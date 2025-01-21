package com.okrama.noteskmp

import androidx.compose.ui.window.ComposeUIViewController
import com.okrama.noteskmp.ui.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }