package com.okrama.noteskmp.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors

@Composable
fun CircularProgressDialog(
    message: String,
) {
    Dialog(onDismissRequest = { /*NOOP*/ }) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = LocalAppColors.current.onPrimaryLight
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    color = LocalAppColors.current.primaryLight
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = message,
                )
            }
        }
    }
}