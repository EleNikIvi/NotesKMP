package com.okrama.noteskmp.ui.core.components.filterrail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.filterrail.FilterChip
import com.okrama.noteskmp.ui.core.components.filterrail.FilterRail
import com.okrama.noteskmp.ui.core.components.filterrail.model.FilterRailItem
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Preview(group = "appearance compact", showBackground = true)
@Composable
private fun FilterRailCompactSizePreview(
    @PreviewParameter(FilterRailPreviewDataProvider::class)
    items: ImmutableList<FilterRailItem>
) {
    NotesKMPTheme {
        FilterRail(
            onFilterCategorySelected = {},
            onAddNewCategory = {},
            filterItems = items,
            selectedItem = items.first(),
        )
    }
}

@Preview(group = "appearance large", showBackground = true)
@Composable
private fun FilterRailLargeSizePreview(
    @PreviewParameter(FilterRailPreviewDataProvider::class)
    items: ImmutableList<FilterRailItem>
) {
    NotesKMPTheme {
        FilterRail(
            onFilterCategorySelected = {},
            onAddNewCategory = {},
            filterItems = items,
            selectedItem = items.first(),
        )
    }
}

@Preview(group = "appearance compact", showBackground = true)
@Composable
private fun FilterChipCompactSizePreview(
    @PreviewParameter(FilterRailPreviewDataProvider::class)
    items: List<FilterRailItem>
) {
    NotesKMPTheme {
        Column(Modifier.padding(horizontal = 16.dp)) {
            FilterChip(
                title = items[0].value ?: "",
                onClick = { },
                isSelected = true,
            )
            FilterChip(
                title = items[1].value ?: "",
                onClick = { },
                isSelected = false,
            )
        }
    }
}

@Preview(group = "appearance large", showBackground = true)
@Composable
private fun FilterChipLargeSizePreview(
    @PreviewParameter(FilterRailPreviewDataProvider::class)
    items: List<FilterRailItem>
) {
    NotesKMPTheme {
        Column(Modifier.padding(horizontal = 16.dp)) {
            FilterChip(
                title = items[0].value ?: "",
                onClick = { },
                isSelected = true,
            )
            FilterChip(
                title = items[1].value ?: "",
                onClick = { },
                isSelected = false,
            )
        }
    }
}

private class FilterRailPreviewDataProvider : PreviewParameterProvider<List<FilterRailItem>> {
    override val values: Sequence<ImmutableList<FilterRailItem>> = sequenceOf(
        listOf(
            FilterRailItem(
                id = 1,
                value = "All",
            ),
            FilterRailItem(
                id = 2,
                value = "Breakfast",
            ),
            FilterRailItem(
                id = 3,
                value = "Lunch",
            )
        ).toImmutableList()
    )
}