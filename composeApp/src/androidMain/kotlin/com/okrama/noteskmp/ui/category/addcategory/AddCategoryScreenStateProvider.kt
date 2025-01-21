package com.okrama.noteskmp.ui.category.addcategory

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.okrama.noteskmp.ui.core.LoremIpsum

class AddCategoryScreenStateProvider : PreviewParameterProvider<AddCategoryScreenState> {
    override val values: Sequence<AddCategoryScreenState> = sequenceOf(
        AddCategoryScreenState(
            imageUrl = "",
            title = "Title of new note",
            description = LoremIpsum.PARAGRAPH,
            canSave = true,
        )
    )
}