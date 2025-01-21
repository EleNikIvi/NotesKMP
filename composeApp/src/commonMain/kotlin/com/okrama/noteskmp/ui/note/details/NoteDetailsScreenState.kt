package com.okrama.noteskmp.ui.note.details

import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.EMPTY_NOTE_ID
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize
import com.okrama.noteskmp.ui.core.components.inputfields.model.DropdownField
import com.okrama.noteskmp.ui.core.model.CategoryModel
import com.okrama.noteskmp.ui.core.model.CategoryUtil.CATEGORY_ALL
import com.okrama.noteskmp.ui.core.model.CategoryUtil.CATEGORY_MODEL_ALL


data class NoteDetailsScreenState(
    val id: Long = EMPTY_NOTE_ID,
    val title: String = "",
    val category: CategoryModel = CATEGORY_MODEL_ALL,
    val description: String = "",
    val shoppingListDropdown: DropdownField = DropdownField(),
)

@CommonParcelize
data class NoteDetailsPersistedState(
    val title: String = "",
    val category: Category = CATEGORY_ALL,
    val description: String = "",
) : CommonParcelable

fun checkAndGetCategory(category: Category): Category =
    if (category.categoryId == CATEGORY_ALL.categoryId) CATEGORY_ALL else category