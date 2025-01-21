package com.okrama.noteskmp.ui.core.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun TypographyPreview() {
    val textStyles = listOf(
        "headingXLarge" to Typography.headingXLarge,
        "headingLarge" to Typography.headingLarge,
        "headingMedium" to Typography.headingMedium,
        "headingSmall" to Typography.headingSmallStrong,
        "bodyLarge" to Typography.bodyLarge,
        "bodyLargeStrong" to Typography.bodyLargeStrong,
        "bodyMedium" to Typography.bodyMedium,
        "bodyMediumStrong" to Typography.bodyMediumStrong,
        "bodySmall" to Typography.bodySmall,
        "bodySmallStrong" to Typography.bodySmallStrong,
        "buttonLarge" to Typography.buttonLarge,
        "buttonMedium" to Typography.buttonMedium,
        "buttonExtraLarge" to Typography.buttonExtraLarge,
        "captionMedium" to Typography.captionMedium,
        "captionMediumStrong" to Typography.captionMediumStrong,
        "codeMediumStrong" to Typography.codeMediumStrong,
        "codeSmall" to Typography.codeSmall,
        "codeSmallStrong" to Typography.codeSmallStrong
    )
    NotesKMPTheme {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            textStyles.forEach { (name, style) ->
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(160.dp),
                        text = name,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Almost before we knew it, we had left the ground.",
                        style = style
                    )
                }
                HorizontalDivider()
            }
        }
    }
}