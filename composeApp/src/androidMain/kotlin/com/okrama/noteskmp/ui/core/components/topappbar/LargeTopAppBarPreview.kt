package com.okrama.noteskmp.ui.core.components.topappbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview
@Composable
private fun LargeTopAppBarPreview() {
    NotesKMPTheme {
        Column(
            Modifier
                .wrapContentSize()
        ) {
            LargeTopAppBar(
                search = "",
                title = "Title",
            )
            Spacer(modifier = Modifier.height(16.dp))
            LargeTopAppBar(
                search = "Some search text",
                title = "Title",
                isCollapsed = true,
            )
        }
    }
}