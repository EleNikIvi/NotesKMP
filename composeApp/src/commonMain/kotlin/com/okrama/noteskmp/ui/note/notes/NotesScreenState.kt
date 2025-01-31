package com.okrama.noteskmp.ui.note.notes

import androidx.compose.runtime.Immutable
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.Note
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize
import com.okrama.noteskmp.ui.core.components.filterrail.model.FILTER_ALL
import com.okrama.noteskmp.ui.core.components.filterrail.model.FilterRailItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class NotesScreenState(
    val notes: List<Note> = emptyList(),
    val search: String = "",
    val categories: ImmutableList<FilterRailItem> = persistentListOf(),
    val selectedCategory: FilterRailItem = FILTER_ALL,
    val isRefreshing: Boolean = false,
)

@CommonParcelize
data class NotesPersistedState(
    val filterCategories: List<FilterRailItem> = listOf(FILTER_ALL),
) : CommonParcelable

object CategoriesFilterRail {
    fun getCategoriesFilterRail(
        categories: List<Category>,
    ): ImmutableList<FilterRailItem> {
        val filterRails = categories.map { category ->
            getCategoryFilterRail(category)
        }
        if(filterRails.isNotEmpty() && filterRails[0].id == FILTER_ALL.id){
            return filterRails.toImmutableList()
        }
        return mutableListOf(FILTER_ALL).apply {
            addAll(filterRails)
        }.toImmutableList()
    }

    private fun getCategoryFilterRail(
        category: Category,
    ): FilterRailItem {
        return FilterRailItem(
            id = category.categoryId,
            value = category.title,
        )
    }
}