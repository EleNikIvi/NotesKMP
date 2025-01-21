package com.okrama.noteskmp.ui.note.notes.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview(showBackground = true)
@Composable
private fun NotesEmptyMessagePreview() {
    NotesKMPTheme {
        NotesEmptyMessage()
    }
}