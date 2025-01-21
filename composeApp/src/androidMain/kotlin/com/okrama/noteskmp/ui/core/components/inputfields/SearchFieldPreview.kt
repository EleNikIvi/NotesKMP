package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.inputfields.SearchFieldComponent
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview(group = "Search empty")
@Composable
private fun SearchFieldEmptyPreview() {
    NotesKMPTheme {
        Column(Modifier
            .wrapContentSize()
            .padding(16.dp)) {
            SearchFieldComponent(
                searchTerm = "",
                placeholder = "Empty enabled",
                onSearchTermChange = {},
                onSearchFieldClear = {},
                enabled = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchFieldComponent(
                searchTerm = "",
                placeholder = "Empty disabled",
                onSearchTermChange = {},
                onSearchFieldClear = {},
                enabled = false,
            )
        }
    }
}

@Preview(group = "Search populated")
@Composable
private fun SearchFieldPopulatedPreview() {
    NotesKMPTheme {
        Column(Modifier.padding(16.dp)) {
            SearchFieldComponent(
                searchTerm = "Populated enabled",
                onSearchTermChange = {},
                onSearchFieldClear = {},
                enabled = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchFieldComponent(
                searchTerm = "Populated disabled",
                onSearchTermChange = {},
                onSearchFieldClear = {},
                enabled = false,
            )
        }
    }
}