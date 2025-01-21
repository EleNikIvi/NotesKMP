package com.okrama.noteskmp.ui.core.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.add_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    largeAppearance: Boolean = false,
) {
    val buttonHeight by height(largeAppearance)
    val buttonTextSize by fontSize(largeAppearance)
    val horizontalGap by horizontalGap(largeAppearance)
    val contentPadding by contentPadding(largeAppearance)

    Button(
        modifier = modifier
            .shadow(
                elevation = 50.dp,
                ambientColor = LocalAppColors.current.primaryLight.copy(alpha = 0.2f)
            )
            .height(height = buttonHeight),
        shape = NotesKMPTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalAppColors.current.primaryContainerLight,
            contentColor = LocalAppColors.current.onPrimaryContainerLight,
        ),
        contentPadding = contentPadding,
        onClick = onClick
    ) {
        Icon(
            painter = rememberVectorPainter(image = Icons.TwoTone.Add),
            contentDescription = stringResource(Res.string.add_label),
        )
        Spacer(modifier = Modifier.width(horizontalGap))
        Text(
            modifier = Modifier
                .weight(weight = 1f, fill = false),
            text = stringResource(Res.string.add_label),
            style = NotesKMPTheme.typography.buttonLarge,
            fontSize = buttonTextSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
private fun contentPadding(largeAppearance: Boolean): State<PaddingValues> = rememberUpdatedState(
    newValue = PaddingValues(
        start = if (largeAppearance) 20.dp else 14.dp,
        end = if (largeAppearance) 24.dp else 16.dp,
    )
)

@Composable
private fun height(largeAppearance: Boolean): State<Dp> =
    rememberUpdatedState(newValue = if (largeAppearance) 56.dp else 36.dp)

@Composable
private fun fontSize(largeAppearance: Boolean): State<TextUnit> =
    rememberUpdatedState(newValue = if (largeAppearance) 18.sp else 16.sp)

@Composable
private fun horizontalGap(largeAppearance: Boolean): State<Dp> =
    rememberUpdatedState(newValue = if (largeAppearance) 12.dp else 10.dp)
