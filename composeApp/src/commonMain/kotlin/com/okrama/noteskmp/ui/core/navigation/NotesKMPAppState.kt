package com.okrama.noteskmp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Destinations used in the [NotesKMPApp].
 */
sealed class NotesDest(val route: String) {
    data object Notes : NotesDest("notes")
    data object AddNote : NotesDest("addNote")
    data object EditNote : NotesDest("editNote")
    data object NoteDetails : NotesDest("noteDetail")
    data object AddCategory : NotesDest("addCategory")
}

/**
 * Remembers and creates an instance of [NotesKMPAppState]
 */
@Composable
fun rememberNotesKMPNavController(
    navController: NavHostController = rememberNavController()
): NotesKMPAppState = remember(navController) {
    NotesKMPAppState(navController)
}

class NotesKMPAppState(
    val navController: NavHostController,
) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun <T> upPressWithResult(data: T?, key: String) {
        // Pass result back
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(key, data)
        navController.navigateUp()
    }

    fun navigateToAddNewNote(from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(NotesDest.AddNote.route)
        }
    }

    fun navigateToEditNote(noteId: Long, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${NotesDest.EditNote.route}/$noteId")
        }
    }

    fun navigateToNoteDetails(noteId: Long, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${NotesDest.NoteDetails.route}/$noteId")
        }
    }

    fun navigateToAddNewCategory(from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(NotesDest.AddCategory.route)
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED