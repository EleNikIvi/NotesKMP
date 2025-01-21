package com.okrama.noteskmp.ui.note.details.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.note.details.NoteDetailsScreenState


@Preview
@Composable
private fun NoteDetailsScreenPreview(
    @PreviewParameter(NoteDetailsScreenStateProvider::class)
    screenState: NoteDetailsScreenState
) {
    NotesKMPTheme {
        NoteDetailsScreen(
            state = screenState,
            onBackPressed = {},
            onEditNote = {},
        )
    }
}