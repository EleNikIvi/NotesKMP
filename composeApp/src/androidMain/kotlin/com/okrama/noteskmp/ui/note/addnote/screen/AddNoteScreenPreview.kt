package com.okrama.noteskmp.ui.note.addnote.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.note.addnote.AddNoteScreenState


@Preview
@Composable
private fun AddNoteScreenPreview(
    @PreviewParameter(AddNoteScreenStateProvider::class)
    screenState: AddNoteScreenState
) {
    NotesKMPTheme {
        AddNoteScreen(
            state = screenState,
            onTitleChange = {},
            onDescriptionChange = {},
            onCategoryChange = {},
            onAddNewCategory = {},
            onSaveNote = {},
            upPress = {},
        )
    }
}