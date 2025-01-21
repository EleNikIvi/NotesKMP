package com.okrama.noteskmp.ui.note.addnote

sealed interface AddNoteSideEffect {
    data object NavigateToAddCategoryScreen : AddNoteSideEffect
    data object NavigateBack : AddNoteSideEffect
}