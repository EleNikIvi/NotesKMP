package com.okrama.noteskmp.ui.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors

@Composable
fun CardComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NotesKMPTheme.shapes.medium,
    backgroundColor: Color = LocalAppColors.current.backgroundLight,
    contentColor: Color = LocalAppColors.current.onBackgroundLight,
    border: BorderStroke? = null,
    elevation: Dp = NotesKMPTheme.elevation.small,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enforceTouchTargetSize: Dp = 48.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides enforceTouchTargetSize
    ) {
        Card(
            onClick = onClick,
            modifier = modifier.shadow(
                elevation = elevation,
                shape = shape,
                spotColor = LocalAppColors.current.onPrimaryContainerLight,
            ),
            enabled = enabled,
            shape = shape,
            colors = CardDefaults.cardColors(
                contentColor = contentColor,
                containerColor = backgroundColor,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            border = border,
            interactionSource = interactionSource,
            content = content
        )
    }
}
