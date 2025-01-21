package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.max_length_format
import org.jetbrains.compose.resources.stringResource


@Composable
fun NotesTextField(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    onTextChange: (String) -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(
                text = placeholder,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        enabled = true,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        interactionSource = interactionSource,
        colors = textFieldColors(),
        shape = NotesKMPTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .hoverable(
                enabled = enabled,
                interactionSource = interactionSource,
            ),
    )
}

@Composable
fun NotesTextFieldWithLimit(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    onTextChange: (String) -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    singleLine: Boolean = true,
    maxTitleLength: Int = 15,
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = {
            if (it.length <= maxTitleLength) {
                onTextChange(it)
            }
        },
        supportingText = {
            Text(
                color = LocalAppColors.current.onPrimaryContainerLight,
                text = stringResource(Res.string.max_length_format, text.length, maxTitleLength),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        enabled = true,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        interactionSource = interactionSource,
        colors = textFieldColors(),
        shape = NotesKMPTheme.shapes.small,
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp).hoverable(
            enabled = enabled,
            interactionSource = interactionSource,
        ),
    )
}

@Composable
internal fun defaultTextSelectionColors(): TextSelectionColors {
    val interactiveColor = LocalAppColors.current.onPrimaryContainerLight
    return remember(interactiveColor) {
        TextSelectionColors(
            handleColor = interactiveColor, backgroundColor = interactiveColor.copy(alpha = .3f)
        )
    }
}

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = LocalAppColors.current.onSurfaceLight,
    focusedContainerColor = LocalAppColors.current.surfaceLight,
    unfocusedContainerColor = LocalAppColors.current.surfaceLight,
    disabledContainerColor = LocalAppColors.current.surfaceLight,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedLabelColor = LocalAppColors.current.onSurfaceVariantLight,
    unfocusedLabelColor = LocalAppColors.current.onSurfaceVariantLight,
    focusedPlaceholderColor = LocalAppColors.current.onSurfaceVariantLight,
    disabledPlaceholderColor = LocalAppColors.current.onSurfaceVariantLight,
)
