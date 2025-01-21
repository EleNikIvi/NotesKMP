package com.okrama.noteskmp.ui.core.components.inputfields

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun getStringValue(valueResId: StringResource?, value: String?) : String {
    return valueResId?.let { stringResource(it) } ?: value ?: ""
}