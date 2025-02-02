package com.okrama.noteskmp.ui.note.addnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.domain.note.NoteInteractor
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.EMPTY_NOTE_ID
import com.okrama.noteskmp.ui.core.flow.SaveableStateFlow.Companion.saveableStateFlow
import com.okrama.noteskmp.ui.core.model.CategoryUtil
import com.okrama.noteskmp.ui.note.addnote.Categories.getCategoriesDropdown
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val NOTE_ID_KEY = "noteId"

class AddNoteViewModel(
    private val noteInteractor: NoteInteractor,
    private val categoryInteractor: CategoryInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _noteId =
        savedStateHandle.get<Long>(NOTE_ID_KEY) ?: EMPTY_NOTE_ID
    private val _persistedState = savedStateHandle.saveableStateFlow(
        key = "add-note-view-model-state-key",
        initialValue = constructInitialPersistedState(),
    )

    private val _initialTitle = MutableStateFlow("")
    private val _initialDescription = MutableStateFlow("")
    private val _initialCategory = MutableStateFlow(CategoryUtil.CATEGORY_ALL)
    private val _categories = savedStateHandle.saveableStateFlow(
        key = "add-note-view-model-categories-key",
        initialValue = emptyList<Category>(),
    )

    private var noteJob: Job? = null
    private var categoryForNoteJob: Job? = null
    private var allCategoriesJob: Job? = null

    private val _sideEffect = Channel<AddNoteSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val screenState: StateFlow<AddNoteScreenState> =
        _persistedState.asStateFlow().mapLatest { persistedState ->

            AddNoteScreenState(
                title = persistedState.title,
                description = persistedState.description,
                categoriesDropdown = persistedState.categoriesDropdown,
                canSave = persistedState.title.isNotBlank() && persistedState.isChanged,
            )
        }.onStart {
            if (_noteId != EMPTY_NOTE_ID) {
                observeNoteDetails()
                observeCategoryForNote()
            }
            observeAllCategories()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddNoteScreenState()
        )

    fun onAddCategorySelected() {
        viewModelScope.launch {
            _sideEffect.send(
                AddNoteSideEffect.NavigateToAddCategoryScreen
            )
        }
    }

    fun onNoteNameChange(noteName: String) {
        _persistedState.update {
            it.copy(
                title = noteName,
            )
        }
        updateIsChanged()
    }

    fun onNoteDescriptionChange(noteDescription: String) {
        _persistedState.update {
            it.copy(
                description = noteDescription,
            )
        }
        updateIsChanged()
    }

    fun onCategoryChange(categoryId: Long) {
        _categories.value.find { it.categoryId == categoryId }?.let { selectedCategory ->
            updateSelectedCategory(selectedCategory)
        } ?: run {
            updateSelectedCategory(CategoryUtil.CATEGORY_ALL)
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            val noteId = if (_noteId != EMPTY_NOTE_ID) {
                noteInteractor.updateNote(
                    id = _noteId,
                    title = _persistedState.value.title.trim(),
                    description = _persistedState.value.description.trim(),
                    categoryId = _persistedState.value.selectedCategory.categoryId,
                )
            } else {
                noteInteractor.addNote(
                    title = _persistedState.value.title.trim(),
                    description = _persistedState.value.description.trim(),
                    categoryId = _persistedState.value.selectedCategory.categoryId,
                )

            }
            if (noteId > 0) {
                _sideEffect.send(
                    AddNoteSideEffect.NavigateBack
                )
            }
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _sideEffect.send(
                AddNoteSideEffect.NavigateBack
            )
        }
    }

    private fun observeNoteDetails() {
        noteJob?.cancel()
        noteJob = noteInteractor.getNoteFlow(_noteId)
            .onEach { note ->
                _initialTitle.value = note.title
                _initialDescription.value = note.description
                _persistedState.update {
                    it.copy(
                        title = note.title,
                        description = note.description,
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun observeCategoryForNote() {
        categoryForNoteJob?.cancel()
        categoryForNoteJob =
            categoryInteractor.getCategoryForNote(_noteId).onEach { noteWithCategory ->
                noteWithCategory?.category?.let { category ->
                    updateSelectedCategory(category)
                    _initialCategory.value = category
                } ?: run {
                    updateSelectedCategory(CategoryUtil.CATEGORY_ALL)
                    _initialCategory.value = CategoryUtil.CATEGORY_ALL
                }
            }.launchIn(viewModelScope)
    }

    private fun observeAllCategories() {
        allCategoriesJob?.cancel()
        allCategoriesJob = categoryInteractor.getCategories()
            .onEach { values ->
                _categories.value = values
                _persistedState.update {
                    it.copy(
                        categoriesDropdown = getCategoriesDropdown(
                            values,
                            it.selectedCategory
                        )
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun updateSelectedCategory(selectedCategory: Category) {
        _persistedState.update {
            it.copy(
                selectedCategory = selectedCategory,
                categoriesDropdown = it.categoriesDropdown.copy(
                    value = selectedCategory.title
                )
            )
        }
        updateIsChanged()
    }

    private fun updateIsChanged() {
        val isUpdated = _initialTitle.value != _persistedState.value.title
                || _initialDescription.value != _persistedState.value.description
                || (_noteId != EMPTY_NOTE_ID && _initialCategory.value != _persistedState.value.selectedCategory)


        _persistedState.update {
            it.copy(
                isChanged = isUpdated,
            )
        }
    }

    private fun constructInitialPersistedState(): AddNotePersistedState =
        AddNotePersistedState()
}