package com.okrama.noteskmp.ui.core.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.button.ButtonAdd
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import androidx.compose.ui.Modifier

@Preview
@Composable
fun ButtonAddPreview() {
    NotesKMPTheme {
        ButtonAdd(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp),
            largeAppearance = false
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonAddWithLargeAppearancePreview() {
    NotesKMPTheme {
        ButtonAdd(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp),
            largeAppearance = true
        )
    }
}