package com.okrama.noteskmp.ui.note.notes.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.okrama.noteskmp.model.Note
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.button_delete
import noteskmp.composeapp.generated.resources.button_edit
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    note: Note,
    onNoteSelected: (Long) -> Unit,
    onDeleteNote: (Long) -> Unit,
    onEditNote: (Long) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .combinedClickable(
                onLongClick = {
                    isExpanded = true
                }, onClick = { onNoteSelected(note.noteId) }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = NotesKMPTheme.elevation.small
        ),
        colors = CardDefaults.cardColors(
            containerColor = LocalAppColors.current.tertiaryContainerLight
        )
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .semantics { contentDescription = note.title },
                color = LocalAppColors.current.onTertiaryContainerLight,
                text = note.title,
                style = NotesKMPTheme.typography.headingMediumCursive,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { contentDescription = note.title },
                color = LocalAppColors.current.onTertiaryContainerLight,
                text = note.description,
                style = NotesKMPTheme.typography.bodyLarge,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )

            NotesDropdownMenu(
                isExpanded = isExpanded,
                onDismiss = { isExpanded = false },
                onDeleteNote = {
                    isExpanded = false
                    onDeleteNote(note.noteId)
                },
                onEditNote = {
                    isExpanded = false
                    onEditNote(note.noteId)
                },
            )
        }
    }
}

@Composable
private fun NotesDropdownMenu(
    isExpanded: Boolean,
    onDismiss: () -> Unit,
    onDeleteNote: () -> Unit,
    onEditNote: () -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.button_delete)) },
            onClick = {
                onDeleteNote()
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.button_edit)) },
            onClick = {
                onEditNote()
            }
        )
    }
}

