package com.okrama.noteskmp.ui.core.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.components.inputfields.defaultTextSelectionColors
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors

@Composable
fun NotesKMPTheme(
    content: @Composable () -> Unit
) {
    val selectionColors = defaultTextSelectionColors()
    MaterialTheme {
        CompositionLocalProvider(
            LocalNotesKMPTypography provides Typography,
            LocalTextSelectionColors provides selectionColors,
            LocalAppColors provides extendedColors,
            content = content,
        )
    }
}

object NotesKMPTheme {
    val typography: NotesKMPTypography
        @Composable
        get() = LocalNotesKMPTypography.current

    val LocalAppColors = staticCompositionLocalOf { extendedColors }

    val elevation = Elevation()
    val shapes = Shapes()

    data class Elevation internal constructor(
        val small: Dp = 8.dp,
        val medium: Dp = 12.dp
    )

    data class Shapes internal constructor(
        val small: CornerBasedShape = RoundedCornerShape(4.dp),
        val medium: CornerBasedShape = RoundedCornerShape(8.dp),
        val large: CornerBasedShape = RoundedCornerShape(12.dp),
        val xLarge: CornerBasedShape = RoundedCornerShape(16.dp),
        val xxLarge: CornerBasedShape = RoundedCornerShape(20.dp),
        val dialog: CornerBasedShape = RoundedCornerShape(28.dp)
    )
}