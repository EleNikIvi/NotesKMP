package com.okrama.noteskmp.ui.note.addnote.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.okrama.noteskmp.ui.core.ObserveAsEvents
import com.okrama.noteskmp.ui.core.bringIntoView
import com.okrama.noteskmp.ui.core.components.SmallTopAppBarScreenContainer
import com.okrama.noteskmp.ui.core.components.inputfields.NotesTextField
import com.okrama.noteskmp.ui.note.addnote.AddNoteScreenState
import com.okrama.noteskmp.ui.note.addnote.AddNoteSideEffect
import com.okrama.noteskmp.ui.note.addnote.AddNoteViewModel
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.note_description_placeholder
import noteskmp.composeapp.generated.resources.note_name_placeholder
import noteskmp.composeapp.generated.resources.title_new_note
import org.jetbrains.compose.resources.stringResource


@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel,
    onAddCategory: () -> Unit,
    upPress: () -> Unit,
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is AddNoteSideEffect.NavigateToAddCategoryScreen -> onAddCategory()

            is AddNoteSideEffect.NavigateBack -> upPress()
        }
    }

    AddNoteScreen(
        state = screenState,
        onTitleChange = viewModel::onNoteNameChange,
        onDescriptionChange = viewModel::onNoteDescriptionChange,
        onCategoryChange = viewModel::onCategoryChange,
        onSaveNote = viewModel::saveNote,
        onAddNewCategory = viewModel::onAddCategorySelected,
        upPress = viewModel::onBackPressed
    )
}

@Composable
fun AddNoteScreen(
    state: AddNoteScreenState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCategoryChange: (Long) -> Unit,
    onAddNewCategory: () -> Unit,
    onSaveNote: () -> Unit,
    upPress: () -> Unit,
) {
    val scrollState = rememberScrollState()
    SmallTopAppBarScreenContainer(
        title = stringResource(Res.string.title_new_note),
        upPress = upPress,
        onAction = onSaveNote,
        actionButtonEnabled = state.canSave,
        scrollState = scrollState,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        CategorySpinner(
            modifier = Modifier.padding(horizontal = 16.dp),
            categoriesDropdown = state.categoriesDropdown,
            onCategoryChange = onCategoryChange,
            onAddNewCategory = onAddNewCategory,
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotesTextField(
            text = state.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            onTextChange = onTitleChange,
            placeholder = stringResource(Res.string.note_name_placeholder),
            imeAction = ImeAction.Next,
            singleLine = true,
        )
        NotesTextField(
            text = state.description,
            modifier = Modifier
                .padding(16.dp)
                .bringIntoView(scrollState),
            onTextChange = onDescriptionChange,
            placeholder = stringResource(Res.string.note_description_placeholder),
            imeAction = ImeAction.Default,
        )
    }
}

