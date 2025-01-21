package com.okrama.noteskmp.ui.core.theme

import androidx.compose.ui.graphics.Color


val DarkDesaturatedCyan = Color(0xFF3D6657) //tertiaryLight
val Green50 = Color(0xFF606219) //primaryLight
val Green40 = Color(0xFF666737) //inversePrimaryContainerLight
val Green30 = Color(0xFFCACB77) //inversePrimaryLight
val Green20 = Color(0xFFE6E890) //primaryContainerLight
val Green10 = Color(0xFF84976D) //tertiaryContainerLight

val White10 = Color(0xFFFFFFFF) //onPrimaryLight
val White20 = Color(0xFFFDF9EC) // backgroundLight surfaceLight
val White30 = Color(0xFFC9C7B6) //outlineVariantLight

val Brown10 = Color(0xFF1C1D00) //onPrimaryContainerLight
val Brown20 = Color(0xFF1C1C14) // onBackgroundLight onSurfaceLight

val Black = Color(0xFF000000) //onTertiaryContainerLight

val Red10 = Color(0xFFBA1A1A)
val Red20 = Color(0xFF410002) // onErrorContainerLight

val LightPink = Color(0xFFFFDAD6) //errorContainerLight

val Gray10 = Color(0xFF797869) //outlineLight
val Gray20 = Color(0xFF48473A) //onSurfaceVariantLight

data class ExtendedColors(
    val primaryLight: Color,
    val onPrimaryLight: Color,
    val primaryContainerLight: Color,
    val onPrimaryContainerLight: Color,
    val tertiaryLight: Color,
    val tertiaryContainerLight: Color,
    val onTertiaryContainerLight: Color,
    val errorLight: Color,
    val onErrorLight: Color,
    val errorContainerLight: Color,
    val onErrorContainerLight: Color,
    val backgroundLight: Color,
    val onBackgroundLight: Color,
    val surfaceLight: Color,
    val onSurfaceLight: Color,
    val onSurfaceVariantLight: Color,
    val outlineLight: Color,
    val outlineVariantLight: Color,
    val inversePrimaryLight: Color,
    val inversePrimaryContainerLight: Color,
)

val extendedColors = ExtendedColors(
    primaryLight = Green50,
    onPrimaryLight = White10,
    primaryContainerLight = Green20,
    onPrimaryContainerLight = Brown10,
    tertiaryLight = DarkDesaturatedCyan,
    tertiaryContainerLight = Green10,
    onTertiaryContainerLight = Black,
    errorLight = Red10,
    onErrorLight = White10,
    errorContainerLight = LightPink,
    onErrorContainerLight = Red20,
    backgroundLight = White20,
    onBackgroundLight = Brown20,
    surfaceLight = White20,
    onSurfaceLight = Brown20,
    onSurfaceVariantLight = Gray20,
    outlineLight = Gray10,
    outlineVariantLight = White30,
    inversePrimaryLight = Green30,
    inversePrimaryContainerLight = Green40,
)