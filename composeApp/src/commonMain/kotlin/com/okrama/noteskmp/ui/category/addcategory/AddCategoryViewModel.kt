package com.okrama.noteskmp.ui.category.addcategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.ui.core.flow.SaveableStateFlow.Companion.saveableStateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val CATEGORY_ID_KEY = "categoryId"

class AddCategoryViewModel(
    private val categoryInteractor: CategoryInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _categoryName = savedStateHandle.saveableStateFlow(
        key = "add_category-view-model-name-key",
        initialValue = "",
    )

    private val _sideEffect = Channel<AddCategorySideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val screenState: StateFlow<AddCategoryScreenState> =
        _categoryName.asStateFlow().mapLatest { categoryName ->
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