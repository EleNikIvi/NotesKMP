package com.okrama.noteskmp.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview(showSystemUi = true)
@Composable
private fun CircularProgressMaterial3Preview() {
    NotesKMPTheme {
        CircularProgressDialog(
            message = "LoremIpsum",
        )
    }
}
