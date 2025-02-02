package com.okrama.noteskmp.ui.note.notes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.domain.note.NoteInteractor
import com.okrama.noteskmp.model.Note
import com.okrama.noteskmp.ui.core.components.filterrail.model.FILTER_ALL
import com.okrama.noteskmp.ui.core.components.filterrail.model.FilterRailItem
import com.okrama.noteskmp.ui.core.flow.SaveableStateFlow.Companion.saveableStateFlow
import com.okrama.noteskmp.ui.note.notes.CategoriesFilterRail.getCategoriesFilterRail
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal const val SEARCH_KEY = "notes-view-model-search-key"
internal const val SELECTED_CATEGORY_KEY = "notes-view-model-selected-category-key"

class NotesViewModel(
    savedStateHandle: SavedStateHandle,
    private val noteInteractor: NoteInteractor,
    private val categoryInteractor: CategoryInteractor,
) : ViewModel() {
    private val _persistedState = savedStateHandle.saveableStateFlow(
        key = "notes-view-model-state-key",
        initialValue = constructInitialPersistedState(),
    )
    private val _allNotes = savedStateHandle.saveableStateFlow(
        key = "notes-view-model-list-key",
        initialValue = emptyList<Note>(),
    )
    private val _notesForCategory = savedStateHandle.saveableStateFlow(
        key = "notes_view-model-notes-category-list-key",
        initialValue = emptyList<Note>(),
    )
    private val _searchTerm = savedStateHandle.saveableStateFlow(
        key = SEARCH_KEY,
        initialValue = "",
    )
    private val _selectedCategory = savedStateHandle.saveableStateFlow(
        key = SELECTED_CATEGORY_KEY,
        initialValue = FILTER_ALL,
    )

    private var allNotesJob: Job? = null
    private var allCategoriesJob: Job? = null

    private val _sideEffect = Channel<NotesSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    val screenState: StateFlow<NotesScreenState> = combine(
        _allNotes.asStateFlow(),
        _notesForCategory.asStateFlow(),
        _persistedState.asStateFlow(),
        _searchTerm.asStateFlow(),
        _selectedCategory.asStateFlow(),
    ) { notes, notesForCategory, persistedState, searchTerm, selectedCategory ->

        val currentNotes = if (selectedCategory == FILTER_ALL) notes
        else notesForCategory

        val filteredNotes =
            currentNotes.filter {
                it.title.contains(
                    other = searchTerm.trim(),
                    ignoreCase = true
                )
            }

        NotesScreenState(
            notes = filteredNotes,
            categories = persistedState.filterCategories.toImmutableList(),
            search = searchTerm,
            selectedCategory = _selectedCategory.value,
        )
    }.onStart {
        observeAllNotes()
        observeAllCategories()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = constructInitialUiState()
    )

    fun onSearchTermChange(searchTerm: String) {
        _searchTerm.value = searchTerm
    }

    fun onSearchFieldClear() {
        _searchTerm.update { "" }
    }

    fun onDeleteNote(noteId: Long) {
        viewModelScope.launch {
            noteInteractor.deleteNote(
                noteId = noteId,
            )
        }
    }

    fun onCategoryChange(categoryId: Long) {
        viewModelScope.launch {
            _persistedState.value.filterCategories.find { it.id == categoryId }
                ?.let { selectedCategory ->
                    _selectedCategory.update { selectedCategory }
                    loadNotes(selectedCategory)
                }
        }
    }

    fun onAddNoteSelected() {
        viewModelScope.launch {
            _sideEffect.send(
                NotesSideEffect.NavigateToAddNoteScreen
            )
        }
    }

    fun onEditNoteSelected(noteId: Long) {
        viewModelScope.launch {
            _sideEffect.send(
                NotesSideEffect.NavigateToEditNoteScreen(noteId)
            )
        }
    }

    fun onNoteSelected(noteId: Long) {
        viewModelScope.launch {
            _sideEffect.send(
                NotesSideEffect.NavigateToNoteDetailsScreen(noteId)
            )
        }
    }

    fun onAddCategorySelected() {
        viewModelScope.launch {
            _sideEffect.send(
                NotesSideEffect.NavigateToAddCategoryScreen
            )
        }
    }

    private fun observeAllNotes() {
        allNotesJob?.cancel()
        allNotesJob = noteInteractor.getAllNotesFlow().onEach { values ->
            _allNotes.update { values }
            loadNotes(_selectedCategory.value)
        }.launchIn(viewModelScope)
    }

    private fun observeAllCategories() {
        allCategoriesJob?.cancel()
        allCategoriesJob = categoryInteractor.getCategories()
            .onEach { values ->
                _persistedState.update {
                    it.copy(
                        filterCategories = getCategoriesFilterRail(values)
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun loadNotes(category: FilterRailItem) {
        viewModelScope.launch {
            val notesForCategory = noteInteractor.getNotesBy(category.id)?.notes

            updateNotes(notesForCategory)
        }
    }

    private fun updateNotes(notes: List<Note>?) {
        _notesForCategory.update { notes ?: emptyList() }
    }

    private fun constructInitialUiState(): NotesScreenState = NotesScreenState()

    private fun constructInitialPersistedState(): NotesPersistedState = NotesPersistedState()
}