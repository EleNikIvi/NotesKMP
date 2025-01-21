package com.okrama.noteskmp.ui.note.addnote.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.CardComponent
import com.okrama.noteskmp.ui.core.components.inputfields.SpinnerComponent
import com.okrama.noteskmp.ui.core.components.inputfields.model.DropdownField
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.category_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategorySpinner(
    modifier: Modifier = Modifier,
    categoriesDropdown: DropdownField,
    onCategoryChange: (Long) -> Unit,
    onAddNewCategory: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        SpinnerComponent(
            label = stringResource(Res.string.category_label),
            selectedItem = categoriesDropdown.value,
            spinnerItems = categoriesDropdown.spinnerItems,
            onSelectionChanged = onCategoryChange,
            actionIcon = {
                Spacer(modifier = Modifier.width(16.dp))
                CardComponent(
                    onClick = { onAddNewCategory() },
                    contentColor = LocalAppColors.current.primaryLight,
                    elevation = NotesKMPTheme.elevation.medium,
                ) {
                    Column {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.TwoTone.Add),
                            modifier = Modifier
                                .widthIn(min = 36.dp)
                                .heightIn(min = 36.dp),
                            contentDescription = null,
                        )
                    }
                }
            }
        )
    }
}