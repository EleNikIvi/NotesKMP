package com.okrama.noteskmp.ui.category.addcategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.model.EMPTY_CATEGORY_ID
import com.okrama.noteskmp.ui.core.flow.SaveableStateFlow.Companion.saveableStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val CATEGORY_ID_KEY = "categoryId"

class AddCategoryViewModel(
    private val categoryInteractor: CategoryInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _categoryId =
        savedStateHandle.get<Long>(CATEGORY_ID_KEY) ?: EMPTY_CATEGORY_ID

    private val _categoryName = savedStateHandle.saveableStateFlow(
        key = "add_category-view-model-name-key",
        initialValue = "",
    )
    private val _savedCategoryId = savedStateHandle.saveableStateFlow(
        key = "add_category-view-model-saved-key",
        initialValue = EMPTY_CATEGORY_ID,
    )

    private val _sideEffect = Channel<AddCategorySideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    val screenState: StateFlow<AddCategoryScreenState> = combine(
        _categoryName.asStateFlow(),
        _savedCategoryId.asStateFlow(),
    ) { categoryName, savedCategoryId ->
        val canSave = categoryName.isNotBlank()

        AddCategoryScreenState(
            title = categoryName,
            canSave = canSave,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AddCategoryScreenState()
    )

    fun onCategoryNameChange(categoryName: String) {
        _categoryName.update { categoryName }
    }

    fun saveCategory() {
        viewModelScope.launch {
            val categoryId = categoryInteractor.addCategory(
                title = _categoryName.value.trim(),
            )

            if (categoryId > 0) {
                _sideEffect.send(
                    AddCategorySideEffect.OnCategorySaved(categoryId = categoryId)
                )
            }
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _sideEffect.send(
                AddCategorySideEffect.OnBackPressed
            )
        }
    }
}