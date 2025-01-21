package com.okrama.noteskmp.ui.note.addnote

import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize
import com.okrama.noteskmp.ui.core.components.inputfields.model.DropdownField
import com.okrama.noteskmp.ui.core.components.inputfields.model.SpinnerItem
import com.okrama.noteskmp.ui.core.model.CategoryUtil.CATEGORY_ALL
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class AddNoteScreenState(
    val title: String = "",
    val description: String = "",
    val categoriesDropdown: DropdownField = DropdownField(),
    val canSave: Boolean = false,
)

@CommonParcelize
data class AddNotePersistedState(
    val title: String = "",
    val description: String = "",
    val categoriesDropdown: DropdownField = DropdownField(
        value = CATEGORY_ALL.title,
        spinnerItems = persistentListOf(
            SpinnerItem(
                id = CATEGORY_ALL.categoryId,
                value = CATEGORY_ALL.title,
            )
        )
    ),
    val selectedCategory: Category = CATEGORY_ALL,
    val isChanged: Boolean = false,
) : CommonParcelable

object Categories {
    fun getCategoriesDropdown(
        categories: List<Category>,
        selectedCategory: Category? = null
    ): DropdownField {
        val spinnerItems = categories.map { category ->
            SpinnerItem(
                id = category.categoryId,
                value = category.title,
            )
        }

        return DropdownField(
            value = selectedCategory?.title ?: "",
            spinnerItems = if (spinnerItems.isNotEmpty() && spinnerItems[0].id == CATEGORY_ALL.categoryId) {
                spinnerItems.toImmutableList()
            } else {
                mutableListOf(SpinnerItem(id = 0, value = "ALL")).apply {
                    addAll(spinnerItems)
                }.toImmutableList()
            }
        )
    }
}