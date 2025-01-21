package com.okrama.noteskmp.ui.core.components.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.typography
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.button_save
import noteskmp.composeapp.generated.resources.navigate_back
import org.jetbrains.compose.resources.stringResource

@Composable
fun SmallTopAppBarWithAction(
    title: String,
    upPress: () -> Unit,
    onAction: () -> Unit,
    actionButtonEnabled: Boolean = true,
) {
    val focusManager = LocalFocusManager.current
    SmallTopAppBarWithAction(
        title = title,
        upPress = upPress,
    ) {
        IconButton(
            onClick = {
                focusManager.clearFocus()
                onAction()
            },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = LocalAppColors.current.onPrimaryContainerLight,
            ),
            enabled = actionButtonEnabled,
        ) {
            ButtonDefaults.textButtonColors(
                contentColor = LocalAppColors.current.onPrimaryContainerLight,
            )
            Icon(
                modifier = Modifier.size(32.dp),
                painter = rememberVectorPainter(image = Icons.Sharp.Done),
                contentDescription = stringResource(Res.string.button_save),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarWithAction(
    title: String,
    upPress: () -> Unit,
    actionContent: @Composable () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontFamily = typography.headingMediumCursive.fontFamily,
                fontWeight = typography.headingMediumCursive.fontWeight,
                fontSize = typography.headingMediumCursive.fontSize,
                lineHeight = typography.headingMediumCursive.lineHeight,
            )
        },
        modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(
                        LocalAppColors.current.inversePrimaryLight,
                        LocalAppColors.current.primaryLight
                    )
                )
            ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            IconButton(
                onClick = upPress,
                enabled = true,
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Rounded.ArrowBack),
                    tint = LocalAppColors.current.onPrimaryContainerLight,
                    contentDescription = stringResource(Res.string.navigate_back),
                )
            }
        },
        actions = {
            actionContent()
        },
    )
}