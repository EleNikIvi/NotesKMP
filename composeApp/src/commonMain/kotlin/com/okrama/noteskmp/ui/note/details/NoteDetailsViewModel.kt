package com.okrama.noteskmp.ui.note.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.domain.note.NoteInteractor
import com.okrama.noteskmp.model.EMPTY_NOTE_ID
import com.okrama.noteskmp.ui.core.flow.SaveableStateFlow.Companion.saveableStateFlow
import com.okrama.noteskmp.ui.core.model.CategoryUtil.CATEGORY_ALL
import com.okrama.noteskmp.ui.core.model.CategoryUtil.getCategoryModel
import com.okrama.noteskmp.ui.note.addnote.NOTE_ID_KEY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    private val noteInteractor: NoteInteractor,
    private val categoryInteractor: CategoryInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sideEffect = Channel<NoteDetailsSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    private val _noteId =
        savedStateHandle.get<Long>(NOTE_ID_KEY) ?: EMPTY_NOTE_ID

    private val _persistedState = savedStateHandle.saveableStateFlow(
        key = "note-details-view-model-state-key",
        initialValue = constructInitialPersistedState(),
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val screenState: StateFlow<NoteDetailsScreenState> =
        _persistedState.asStateFlow().mapLatest { persistedState ->

            NoteDetailsScreenState(
                id = _noteId,
                title = persistedState.title,
                category = getCategoryModel(persistedState.category),
                description = persistedState.description,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NoteDetailsScreenState()
        )

    init {
        viewModelScope.launch {
            launch {
                noteInteractor.getNoteFlow(_noteId)
                    .collect { note ->
                        _persistedState.update {
                            it.copy(
                                title = note.title,
                                description = note.description,
                            )
                        }
                    }
            }
            launch {
                categoryInteractor.getCategoryForNote(_noteId).collect { noteWithCategory ->
                    noteWithCategory?.category?.let { category ->
                        _persistedState.update {
                            it.copy(
                                category = checkAndGetCategory(category)
                            )
                        }
                    } ?: run {
                        _persistedState.update {
                            it.copy(
                                category = CATEGORY_ALL
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEditNote() {
        viewModelScope.launch {
            _sideEffect.send(NoteDetailsSideEffect.NavigateToEditNote(_noteId))
        }

    }

    fun onBackPressed() {
        viewModelScope.launch {
            _sideEffect.send(NoteDetailsSideEffect.NavigateBack)
        }

    }

    private fun constructInitialPersistedState(): NoteDetailsPersistedState =
        NoteDetailsPersistedState()
}