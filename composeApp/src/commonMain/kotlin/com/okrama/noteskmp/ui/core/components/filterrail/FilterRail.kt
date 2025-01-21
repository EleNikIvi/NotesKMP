package com.okrama.noteskmp.ui.core.components.filterrail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.CardComponent
import kotlinx.collections.immutable.ImmutableList
import com.okrama.noteskmp.ui.core.components.filterrail.model.FilterRailItem
import com.okrama.noteskmp.ui.core.components.inputfields.getStringValue
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors

@Composable
fun FilterRail(
    filterItems: ImmutableList<FilterRailItem>,
    selectedItem: FilterRailItem,
    onFilterCategorySelected: (Long) -> Unit,
    onAddNewCategory: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    scrollState: ScrollState = rememberScrollState(),
) {
    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        filterItems.forEach { item ->
            FilterChip(
                title = item.value,
                isSelected = item == selectedItem,
                onClick = {
                    onFilterCategorySelected(item.id)
                },
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        AddNewFilterChip(
            onClick = onAddNewCategory,
        )
    }
}

@Composable
internal fun FilterChip(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    CardComponent(
        onClick = onClick,
        backgroundColor = if (isSelected) LocalAppColors.current.inversePrimaryContainerLight else Color.Transparent,
        elevation = if (isSelected) NotesKMPTheme.elevation.small else 0.dp,
        enforceTouchTargetSize = Dp.Unspecified
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        ) {
            Text(
                text = title,
                style = NotesKMPTheme.typography.bodyMediumStrong,
                color = LocalAppColors.current.onPrimaryLight,
                modifier = Modifier
                    .semantics { contentDescription = title },
            )
        }
    }
}

@Composable
private fun AddNewFilterChip(
    onClick: () -> Unit,
) {
    CardComponent(
        onClick = onClick,
        backgroundColor = LocalAppColors.current.primaryContainerLight,
        elevation = NotesKMPTheme.elevation.small,
        enforceTouchTargetSize = Dp.Unspecified
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.TwoTone.Add),
                modifier = Modifier.size(20.dp),
                contentDescription = null,
                tint = LocalAppColors.current.onPrimaryContainerLight,
            )
        }
    }
}
