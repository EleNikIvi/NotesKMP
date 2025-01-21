package com.okrama.noteskmp.ui.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Custom Typography class. Unlike material [androidx.compose.material.Typography], it
 * has more variations for text styles.
 */
@Immutable
data class NotesKMPTypography internal constructor(
    // Heading
    val headingXLarge: TextStyle,
    val headingLarge: TextStyle,
    val headingMedium: TextStyle,
    val headingMediumCursive: TextStyle,
    val headingSmallStrong: TextStyle,
    val headingSmall: TextStyle,
    // Body
    val bodyLarge: TextStyle,
    val bodyLargeStrong: TextStyle,
    val bodyMedium: TextStyle,
    val bodyMediumStrong: TextStyle,
    val bodySmall: TextStyle,
    val bodySmallStrong: TextStyle,
    // Button
    val buttonLarge: TextStyle,
    val buttonMedium: TextStyle,
    val buttonExtraLarge: TextStyle,
    // Caption
    val captionMedium: TextStyle,
    val captionMediumStrong: TextStyle,
    // Code
    val codeMediumStrong: TextStyle,
    val codeSmall: TextStyle,
    val codeSmallStrong: TextStyle,
)

/**
 * Create Typography with required styles
 */
internal val Typography = NotesKMPTypography(
    headingXLarge = TextStyle(
        fontSize = 36.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive
    ),
    headingLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default
    ),
    headingMedium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    headingMediumCursive = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive
    ),
    headingSmallStrong = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    headingSmall = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    ),
    bodyLargeStrong = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    ),
    bodyMediumStrong = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    ),
    bodySmallStrong = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default
    ),
    buttonLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    buttonMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default
    ),
    buttonExtraLarge = TextStyle(
        fontSize = 18.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    captionMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    ),
    captionMediumStrong = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default
    ),
    codeMediumStrong = TextStyle(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    ),
    codeSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    ),
    codeSmallStrong = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    )
)

/**
 * This CompositionLocal holds the default Typography. It can be replaced inside the Theme
 * using CompositionLocalProvider.
 */
internal val LocalNotesKMPTypography = staticCompositionLocalOf {
    NotesKMPTypography(
        headingXLarge = Typography.headingXLarge,
        headingLarge = Typography.headingLarge,
        headingMedium = Typography.headingMedium,
        headingMediumCursive = Typography.headingMediumCursive,
        headingSmallStrong = Typography.headingSmallStrong,
        headingSmall = Typography.headingSmall,
        bodyLarge = Typography.bodyLarge,
        bodyLargeStrong = Typography.bodyLargeStrong,
        bodyMedium = Typography.bodyMedium,
        bodyMediumStrong = Typography.bodyMediumStrong,
        bodySmall = Typography.bodySmall,
        bodySmallStrong = Typography.bodySmallStrong,
        buttonLarge = Typography.buttonLarge,
        buttonMedium = Typography.buttonMedium,
        buttonExtraLarge = Typography.buttonExtraLarge,
        captionMedium = Typography.captionMedium,
        captionMediumStrong = Typography.captionMediumStrong,
        codeMediumStrong = Typography.codeMediumStrong,
        codeSmall = Typography.codeSmall,
        codeSmallStrong = Typography.codeSmallStrong
    )
}