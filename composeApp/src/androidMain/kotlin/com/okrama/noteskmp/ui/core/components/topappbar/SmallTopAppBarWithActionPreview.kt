package com.okrama.noteskmp.ui.core.components.topappbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview
@Composable
private fun SmallTopAppBarEnabledPreview() {
    NotesKMPTheme {
        SmallTopAppBarWithAction(
            title = "New Category",
            upPress = {},
            onAction = {},
            actionButtonEnabled = true,
        )
    }
}

@Preview
@Composable
private fun SmallTopAppBarDisabledPreview() {
    NotesKMPTheme {
        SmallTopAppBarWithAction(
            title = "New Category",
            upPress = {},
            onAction = {},
            actionButtonEnabled = false,
        )
    }
}