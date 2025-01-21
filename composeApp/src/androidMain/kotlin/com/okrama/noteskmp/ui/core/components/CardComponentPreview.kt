package com.okrama.noteskmp.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.CardComponent
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview(group = "clickable", showBackground = true)
@Composable
private fun ClickablePreview() {
    NotesKMPTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardComponent(
                onClick = {}
            ) {
                DummyCardContent(text = "click me")
            }
            CardComponent(
                onClick = {},
                enforceTouchTargetSize = Dp.Unspecified
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "small chip",
                )
            }
        }
    }
}

@Composable
private fun DummyCardContent(text: String) {
    Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}