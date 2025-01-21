package com.okrama.noteskmp.ui.note.details

sealed interface NoteDetailsSideEffect {
    data class NavigateToEditNote(
        val noteId: Long
    ): NoteDetailsSideEffect
    data object NavigateBack: NoteDetailsSideEffect
}