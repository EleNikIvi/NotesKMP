package com.okrama.noteskmp.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview
@Composable
private fun EditScreenContainerPreview() {
    NotesKMPTheme {
        SmallTopAppBarScreenContainer(
            title = "New Category",
            upPress = {},
            onAction = {},
            actionButtonEnabled = true,
            content = {},
        )
    }
}