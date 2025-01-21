package com.okrama.noteskmp.ui.category.addcategory

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.okrama.noteskmp.ui.category.addcategory.screen.AddCategoryScreen
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme

@Preview
@Composable
private fun AddCategoryScreenPreview(
    @PreviewParameter(AddCategoryScreenStateProvider::class)
    screenState: AddCategoryScreenState
) {
    NotesKMPTheme {
        AddCategoryScreen(
            state = screenState,
            onCategoryNameChange = {},
            onSaveCategory = {},
            upPress = {},
        )
    }
}