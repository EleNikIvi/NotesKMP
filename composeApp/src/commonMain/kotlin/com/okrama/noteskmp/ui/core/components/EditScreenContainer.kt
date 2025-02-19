package com.okrama.noteskmp.ui.core.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.okrama.noteskmp.ui.core.components.topappbar.SmallTopAppBarWithAction
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors

@Composable
fun SmallTopAppBarScreenContainer(
    title: String,
    scrollState: ScrollState = rememberScrollState(),
    upPress: () -> Unit,
    onAction: () -> Unit,
    actionButtonEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        topBar = {
            SmallTopAppBarWithAction(
                title = title,
                upPress = upPress,
                onAction = onAction,
                actionButtonEnabled = actionButtonEnabled,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .imePadding()
                .padding(paddingValues)
                .fillMaxSize()
                .background(LocalAppColors.current.primaryLight)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}
