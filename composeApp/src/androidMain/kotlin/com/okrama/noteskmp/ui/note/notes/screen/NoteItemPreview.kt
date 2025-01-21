package com.okrama.noteskmp.ui.note.notes.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.model.Note
import com.okrama.noteskmp.ui.core.LoremIpsum
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview
@Composable
private fun NoteItemPreview() {
    NotesKMPTheme {
        NoteItem(
            note = Note(
                noteId = 1L,
                title = LoremIpsum.SHORT,
                description = LoremIpsum.LONG,
            ),
            onNoteSelected = {},
            onDeleteNote = {},
            onEditNote = {},
        )
    }
}