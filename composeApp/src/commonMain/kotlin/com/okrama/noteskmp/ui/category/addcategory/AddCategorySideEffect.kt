package com.okrama.noteskmp.ui.category.addcategory

sealed interface AddCategorySideEffect {
    data class OnCategorySaved(val categoryId: Long) : AddCategorySideEffect
    data object OnBackPressed : AddCategorySideEffect
}