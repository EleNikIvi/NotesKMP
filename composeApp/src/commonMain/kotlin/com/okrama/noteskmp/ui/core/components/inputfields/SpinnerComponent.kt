package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.inputfields.model.SpinnerItem
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerComponent(
    label: String,
    modifier: Modifier = Modifier,
    selectedItem: String,
    spinnerItems: ImmutableList<SpinnerItem>,
    onSelectionChanged: (Long) -> Unit,
    actionIcon: @Composable (RowScope.() -> Unit) = {},
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            modifier = modifier
                .weight(1f),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                label = {
                    Text(
                        text = label,
                    )
                },
                trailingIcon = { SpinnerIcon(expanded = expanded) },
                colors = textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LocalAppColors.current.backgroundLight),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                spinnerItems.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.value,
                                color = LocalAppColors.current.onSurfaceLight,
                                style = NotesKMPTheme.typography.bodyLarge,
                            )
                        },
                        onClick = {
                            onSelectionChanged(item.id)
                            expanded = false
                        }
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            actionIcon()
        }
    }
}

@Composable
internal fun SpinnerIcon(
    expanded: Boolean,
) {
    Icon(
        painter = rememberVectorPainter(image = Icons.TwoTone.KeyboardArrowDown),
        contentDescription = null,
        modifier = Modifier
            .padding(start = 12.dp, end = 4.dp)
            .rotate(
                if (expanded)
                    180f
                else
                    0f
            )
    )
}