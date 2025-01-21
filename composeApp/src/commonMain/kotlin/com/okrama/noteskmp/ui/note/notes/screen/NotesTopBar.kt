package com.okrama.noteskmp.ui.note.notes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.topappbar.LargeTopAppBar
import com.okrama.noteskmp.ui.core.components.filterrail.FilterRail
import com.okrama.noteskmp.ui.note.notes.NotesScreenState
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.app_title
import noteskmp.composeapp.generated.resources.search_note_hint
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotesTopBar(
    contentState: NotesScreenState,
    isCollapsed: Boolean = false,
    onAddNewNote: () -> Unit = {},
    onSearchTermChange: (String) -> Unit = {},
    onSearchFieldClear: () -> Unit = {},
    onFilterCategorySelected: (Long) -> Unit = {},
    onAddNewCategory: () -> Unit = {},
) {
    LargeTopAppBar(
        search = contentState.search,
        title = stringResource(Res.string.app_title),
        isCollapsed = isCollapsed,
        searchPlaceholder = stringResource(Res.string.search_note_hint),
        onAction = onAddNewNote,
        onSearchTermChange = onSearchTermChange,
        onSearchFieldClear = onSearchFieldClear,
    ) {
        Column(modifier = Modifier.heightIn(min = 16.dp)) {
            FilterRail(
                filterItems = contentState.categories,
                selectedItem = contentState.selectedCategory,
                onFilterCategorySelected = onFilterCategorySelected,
                onAddNewCategory = onAddNewCategory,
                contentPadding = PaddingValues(
                    start = 16.dp, end = 16.dp,
                    top = 12.dp
                ),
            )
        }
    }
}


