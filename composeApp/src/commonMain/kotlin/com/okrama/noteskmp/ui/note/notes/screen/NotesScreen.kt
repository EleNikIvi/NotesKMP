package com.okrama.noteskmp.ui.note.notes.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.okrama.noteskmp.ui.core.ObserveAsEvents
import com.okrama.noteskmp.ui.core.components.topappbar.isToolBarCollapsed
import com.okrama.noteskmp.ui.note.notes.NotesScreenState
import com.okrama.noteskmp.ui.note.notes.NotesSideEffect
import com.okrama.noteskmp.ui.note.notes.NotesViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel<NotesViewModel>(),
    onAddNewNote: () -> Unit,
    onEditNote: (Long) -> Unit,
    onNoteSelected: (Long) -> Unit,
    onAddCategory: () -> Unit,
){
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is NotesSideEffect.NavigateToAddNoteScreen -> onAddNewNote()

            is NotesSideEffect.NavigateToEditNoteScreen -> onEditNote(sideEffect.noteId)

            is NotesSideEffect.NavigateToNoteDetailsScreen -> onNoteSelected(sideEffect.noteId)

            is NotesSideEffect.NavigateToAddCategoryScreen -> onAddCategory()
        }
    }

    NotesScreen(
        screenState = screenState,
        onAddNewNote = viewModel::onAddNoteSelected,
        onEditNote = viewModel::onEditNoteSelected,
        onNoteSelected = viewModel::onNoteSelected,
        onSearchFieldClear = viewModel::onSearchFieldClear,
        onSearchTermChange = viewModel::onSearchTermChange,
        onDeleteNote = viewModel::onDeleteNote,
        onCategoryChange = viewModel::onCategoryChange,
        onAddCategory = viewModel::onAddCategorySelected,
    )
}


@Composable
fun NotesScreen(
    screenState: NotesScreenState,
    onAddNewNote: () -> Unit,
    onEditNote: (Long) -> Unit,
    onNoteSelected: (Long) -> Unit,
    onSearchFieldClear: () -> Unit,
    onSearchTermChange: (String) -> Unit,
    onDeleteNote: (Long) -> Unit,
    onCategoryChange: (Long) -> Unit,
    onAddCategory: () -> Unit,
) {
    val listState = rememberLazyGridState()
    val isCollapsed = isToolBarCollapsed(listState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Color.White,
        topBar = {
            NotesTopBar(
                contentState = screenState,
                isCollapsed = isCollapsed,
                onAddNewNote = onAddNewNote,
                onSearchTermChange = onSearchTermChange,
                onSearchFieldClear = onSearchFieldClear,
                onFilterCategorySelected = onCategoryChange,
                onAddNewCategory = onAddCategory,
            )
        },
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 130.dp),
            state = listState,
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (screenState.notes.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    NotesEmptyMessage()
                }
            } else {
                items(screenState.notes,
                    key = { it.noteId },
                ) { note ->
                    NoteItem(
                        note = note,
                        onNoteSelected = onNoteSelected,
                        onDeleteNote = onDeleteNote,
                        onEditNote = onEditNote,
                    )
                }
            }

        }
    }
}