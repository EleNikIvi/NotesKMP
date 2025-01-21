package com.okrama.noteskmp.ui.note.notes

sealed interface NotesSideEffect {
    data object NavigateToAddNoteScreen : NotesSideEffect

    data class NavigateToEditNoteScreen(
        val noteId: Long
    ) : NotesSideEffect

    data class NavigateToNoteDetailsScreen(
        val noteId: Long
    ) : NotesSideEffect

    data object NavigateToAddCategoryScreen : NotesSideEffect
}