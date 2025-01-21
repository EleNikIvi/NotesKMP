package com.okrama.noteskmp.ui.core.model

import com.okrama.noteskmp.model.Category
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.filter_category_all
import org.jetbrains.compose.resources.StringResource

data class CategoryModel(
    val categoryId: Long = 0,
    val title: String,
    val titleResId: StringResource? = null,
)

object CategoryUtil {
    val CATEGORY_ALL = Category(
        categoryId = 0,
        title = "ALL"
    )

    val CATEGORY_ALL_TITLE_RES_ID = Res.string.filter_category_all

    val CATEGORY_MODEL_ALL = CategoryModel(
        categoryId = 0,
        title = "ALL",
        titleResId = CATEGORY_ALL_TITLE_RES_ID,
    )

    fun getCategoryTitleResId(category: Category): StringResource? {
        return if (category.categoryId == 0L) CATEGORY_ALL_TITLE_RES_ID else null
    }

    fun getCategoryModel(
        category: Category,
    ): CategoryModel {
        return CategoryModel(
            categoryId = category.categoryId,
            title = category.title,
            titleResId = getCategoryTitleResId(category)
        )
    }
}