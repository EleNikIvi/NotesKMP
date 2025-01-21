package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.okrama.noteskmp.ui.core.components.inputfields.model.SpinnerItem
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import kotlinx.collections.immutable.persistentListOf

@Preview(showBackground = true)
@Composable
private fun SpinnerButtonsPreview() {
    NotesKMPTheme {
        Row {
            SpinnerIcon(expanded = true)
            SpinnerIcon(expanded = false)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EnabledSpinnerPreview() {
    NotesKMPTheme {
        Box {
            var spinnerSelectedIndex by rememberSaveable { mutableLongStateOf(1L) }
            SpinnerComponent(
                label = "Enabled spinner",
                selectedItem = OPTIONS[0].value ?: "",
                spinnerItems = OPTIONS,
                onSelectionChanged = { selectedIndex ->
                    spinnerSelectedIndex = selectedIndex
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DisabledSpinnerPreview() {
    NotesKMPTheme {
        Box {
            SpinnerComponent(
                label = "Disabled label",
                selectedItem = OPTIONS[0].value ?: "",
                spinnerItems = OPTIONS,
                onSelectionChanged = {  /*no-op*/ },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpinnerWithActionButtonPreview() {
    NotesKMPTheme {
        Box {
            var spinnerSelectedIndex by rememberSaveable { mutableLongStateOf(1L) }
            SpinnerComponent(
                label = "Spinner with action button",
                selectedItem = OPTIONS[0].value ?: "",
                spinnerItems = OPTIONS,
                onSelectionChanged = { selectedIndex ->
                    spinnerSelectedIndex = selectedIndex
                },
                actionIcon = {
                    IconButton(
                        onClick = { /* no-op */ },
                    ) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Rounded.Add),
                            tint = LocalAppColors.current.primaryLight,
                            contentDescription = "",
                        )
                    }
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpinnerWithPlaceholderTextPreview() {
    NotesKMPTheme {
        Box {
            var spinnerSelectedIndex by rememberSaveable { mutableLongStateOf(0L) }
            SpinnerComponent(
                label = "Spinner with placeholder text",
                selectedItem = OPTIONS[0].value ?: "",
                spinnerItems = OPTIONS,
                onSelectionChanged = { selectedIndex ->
                    spinnerSelectedIndex = selectedIndex
                },
                actionIcon = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpinnerWithLongTextPreview() {
    NotesKMPTheme {
        Box {
            var spinnerSelectedIndex by rememberSaveable { mutableLongStateOf(5L) }
            SpinnerComponent(
                label = "Spinner with long text",
                selectedItem = OPTIONS[0].value ?: "",
                spinnerItems = OPTIONS,
                onSelectionChanged = { selectedIndex ->
                    spinnerSelectedIndex = selectedIndex
                },
                actionIcon = {}
            )
        }
    }
}

private val OPTIONS = persistentListOf(
    SpinnerItem(0L, ""),
    SpinnerItem(1L, "second"),
    SpinnerItem(2L, "third"),
    SpinnerItem(3L, "fourth"),
    SpinnerItem(4L, "fifth"),
    SpinnerItem(5L, "678191"),
)