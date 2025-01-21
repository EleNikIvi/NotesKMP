package com.okrama.noteskmp.ui.core

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import kotlinx.coroutines.launch


fun Modifier.bringIntoView(
    scrollState: ScrollState
): Modifier = composed {
    var scrollToPosition by remember {
        mutableStateOf(0f)
    }
    val coroutineScope = rememberCoroutineScope()
    this
        .onGloballyPositioned { coordinates ->
            scrollToPosition = scrollState.value + coordinates.positionInRoot().y
        }
        .onFocusEvent {
            if (it.isFocused) {
                coroutineScope.launch {
                    scrollState.animateScrollTo(scrollToPosition.toInt())
                }
            }
        }
}