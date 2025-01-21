package com.okrama.noteskmp

import androidx.compose.runtime.Composable
import com.okrama.noteskmp.ui.AppNavGraph
import com.okrama.noteskmp.ui.core.navigation.rememberNotesKMPNavController
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    NotesKMPTheme {
        val notesKMPNavController = rememberNotesKMPNavController()

        AppNavGraph(appState = notesKMPNavController)
    }
}