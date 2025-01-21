package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.button_clear_description
import org.jetbrains.compose.resources.stringResource

private val ICON_SIZE = 24.dp

@Composable
fun SearchFieldComponent(
    searchTerm: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    onSearchTermChange: (String) -> Unit,
    onSearchFieldClear: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    searchClearContentDescription: String = stringResource(Res.string.button_clear_description),
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = searchTerm,
        onValueChange = onSearchTermChange,
        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(image = Icons.TwoTone.Search),
                contentDescription = "",
                modifier = Modifier.size(ICON_SIZE),
                tint = LocalAppColors.current.onSurfaceVariantLight,
            )
        },
        trailingIcon = if (searchTerm.isNotEmpty()) {
            {
                IconButton(onClick = onSearchFieldClear) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.TwoTone.Close),
                        contentDescription = searchClearContentDescription,
                        modifier = Modifier.size(ICON_SIZE),
                        tint = LocalAppColors.current.onSurfaceVariantLight,
                    )
                }
            }
        } else {
            null
        },
        placeholder = {
            placeholder?.let {
                Text(
                    text = it,
                    style = NotesKMPTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        },
        textStyle = NotesKMPTheme.typography.bodyLarge,
        enabled = true,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        interactionSource = interactionSource,
        shape = RoundedCornerShape(14.dp),
        colors = textFieldColors(),
        modifier = modifier
            .hoverable(
                enabled = enabled,
                interactionSource = interactionSource,
            )
            .onFocusChanged {
                if (it.hasFocus) {
                    Logger.d("Search field focused")
                }
            },
    )
}