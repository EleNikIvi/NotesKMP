package com.okrama.noteskmp.ui.category.addcategory.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.okrama.noteskmp.ui.category.addcategory.AddCategoryScreenState
import com.okrama.noteskmp.ui.category.addcategory.AddCategorySideEffect
import com.okrama.noteskmp.ui.category.addcategory.AddCategoryViewModel
import com.okrama.noteskmp.ui.category.addcategory.CATEGORY_ID_KEY
import com.okrama.noteskmp.ui.category.addcategory.MAX_CATEGORY_TITLE_CHAR
import com.okrama.noteskmp.ui.core.components.SmallTopAppBarScreenContainer
import com.okrama.noteskmp.ui.core.components.inputfields.NotesTextFieldWithLimit
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.category_name_placeholder
import noteskmp.composeapp.generated.resources.title_new_category
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddCategoryScreen(
    viewModel: AddCategoryViewModel = koinViewModel<AddCategoryViewModel>(),
    upPressWithResult: (Long, String) -> Unit,
    upPress: () -> Unit,
){
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AddCategorySideEffect.OnCategorySaved -> upPressWithResult(
                    sideEffect.categoryId,
                    CATEGORY_ID_KEY
                )
                is AddCategorySideEffect.OnBackPressed -> upPress()
            }
        }
    }

    AddCategoryScreen(
        state = screenState,
        onCategoryNameChange = viewModel::onCategoryNameChange,
        onSaveCategory = viewModel::saveCategory,
        upPress = viewModel::onBackPressed,
    )
}

@Composable
fun AddCategoryScreen(
    state: AddCategoryScreenState,
    onCategoryNameChange: (String) -> Unit,
    onSaveCategory: () -> Unit,
    upPress: () -> Unit,
) {
    SmallTopAppBarScreenContainer(
        title = stringResource(Res.string.title_new_category),
        upPress = upPress,
        onAction = onSaveCategory,
        actionButtonEnabled = state.canSave,
    ) {
        NotesTextFieldWithLimit(
            modifier = Modifier.padding(16.dp),
            text = state.title,
            onTextChange = onCategoryNameChange,
            placeholder = stringResource(Res.string.category_name_placeholder),
            singleLine = true,
            maxTitleLength = MAX_CATEGORY_TITLE_CHAR,
        )
    }

}