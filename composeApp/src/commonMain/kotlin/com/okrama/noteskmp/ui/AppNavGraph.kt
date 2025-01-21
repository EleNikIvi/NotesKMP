package com.okrama.noteskmp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.okrama.noteskmp.ui.category.addcategory.AddCategorySideEffect
import com.okrama.noteskmp.ui.category.addcategory.AddCategoryViewModel
import com.okrama.noteskmp.ui.category.addcategory.CATEGORY_ID_KEY
import com.okrama.noteskmp.ui.category.addcategory.screen.AddCategoryScreen
import com.okrama.noteskmp.ui.core.ObserveAsEvents
import com.okrama.noteskmp.ui.core.navigation.NotesDest
import com.okrama.noteskmp.ui.core.navigation.NotesKMPAppState
import com.okrama.noteskmp.ui.note.addnote.AddNoteSideEffect
import com.okrama.noteskmp.ui.note.addnote.AddNoteViewModel
import com.okrama.noteskmp.ui.note.addnote.NOTE_ID_KEY
import com.okrama.noteskmp.ui.note.addnote.screen.AddNoteScreen
import com.okrama.noteskmp.ui.note.details.screen.NoteDetailsScreen
import com.okrama.noteskmp.ui.note.notes.screen.NotesScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(appState: NotesKMPAppState) {
    NavHost(
        navController = appState.navController,
        startDestination = NotesDest.Notes.route
    ) {
        notesNavGraph(
            onAddNote = appState::navigateToAddNewNote,
            onNoteSelected = appState::navigateToNoteDetails,
            onEditNote = appState::navigateToEditNote,
            onAddCategory = appState::navigateToAddNewCategory,
            upPress = appState::upPress,
            upPressWithResult = appState::upPressWithResult,
        )
    }
}

private fun NavGraphBuilder.notesNavGraph(
    onAddNote: (NavBackStackEntry) -> Unit,
    onNoteSelected: (Long, NavBackStackEntry) -> Unit,
    onEditNote: (Long, NavBackStackEntry) -> Unit,
    onAddCategory: (NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    upPressWithResult: (Long, String) -> Unit,
) {
    composable(NotesDest.Notes.route) { backStackEntry ->
        NotesScreen(
            onAddNewNote = { onAddNote(backStackEntry) },
            onEditNote = { noteId -> onEditNote(noteId, backStackEntry) },
            onNoteSelected = { noteId -> onNoteSelected(noteId, backStackEntry) },
            onAddCategory = { onAddCategory(backStackEntry) },
        )
    }
    composable(
        route = "${NotesDest.NoteDetails.route}/{$NOTE_ID_KEY}",
        arguments = listOf(navArgument(NOTE_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        NoteDetailsScreen(
            navigateToEditNote = { noteId -> onEditNote(noteId, backStackEntry) },
            upPress = upPress,
        )
    }
    composable(
        "${NotesDest.EditNote.route}/{$NOTE_ID_KEY}",
        arguments = listOf(navArgument(NOTE_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        AddNote(
            backStackEntry = backStackEntry,
            onAddCategory = { onAddCategory(backStackEntry) },
            upPress = upPress,
        )
    }
    composable(NotesDest.AddNote.route) { backStackEntry ->
        AddNote(
            backStackEntry = backStackEntry,
            onAddCategory = { onAddCategory(backStackEntry) },
            upPress = upPress,
        )
    }
    composable(NotesDest.AddCategory.route) {
        AddCategoryScreen(
            upPressWithResult = upPressWithResult,
            upPress = upPress,
        )
    }
}

@Composable
private fun AddNote(
    backStackEntry: NavBackStackEntry,
    onAddCategory: () -> Unit,
    upPress: () -> Unit,
) {
    val viewModel: AddNoteViewModel = koinViewModel<AddNoteViewModel>()

    LaunchedEffect(key1 = viewModel) {
        backStackEntry.savedStateHandle.get<Long>(CATEGORY_ID_KEY)?.let { newCategoryId ->
            viewModel.onCategoryChange(newCategoryId)
        }
    }

    AddNoteScreen(
        viewModel = viewModel,
        onAddCategory = onAddCategory,
        upPress = upPress,
    )
}