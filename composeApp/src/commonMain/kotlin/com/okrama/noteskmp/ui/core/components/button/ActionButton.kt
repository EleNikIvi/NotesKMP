package com.okrama.noteskmp.ui.core.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ActionButton(
    onAction: () -> Unit,
    icon: ImageVector,
    iconContentResId: StringResource
) {
    Box(modifier = Modifier.padding(8.dp)) {
        IconButton(
            onClick = onAction,
            enabled = true,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 25.dp))
                .size(35.dp)
                .background(LocalAppColors.current.outlineLight),
        ) {
            Icon(
                painter = rememberVectorPainter(image = icon),
                tint = LocalAppColors.current.outlineVariantLight,
                contentDescription = stringResource(iconContentResId)
            )
        }
    }
}