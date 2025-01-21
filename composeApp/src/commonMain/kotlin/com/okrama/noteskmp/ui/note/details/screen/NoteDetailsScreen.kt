package com.okrama.noteskmp.ui.note.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.okrama.noteskmp.ui.core.ObserveAsEvents
import com.okrama.noteskmp.ui.core.components.button.ActionButton
import com.okrama.noteskmp.ui.core.components.inputfields.getStringValue
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme
import com.okrama.noteskmp.ui.core.theme.NotesKMPTheme.LocalAppColors
import com.okrama.noteskmp.ui.note.details.NoteDetailsScreenState
import com.okrama.noteskmp.ui.note.details.NoteDetailsSideEffect
import com.okrama.noteskmp.ui.note.details.NoteDetailsViewModel
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.button_edit
import noteskmp.composeapp.generated.resources.navigate_back
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun NoteDetailsScreen(
    viewModel: NoteDetailsViewModel = koinViewModel<NoteDetailsViewModel>(),
    navigateToEditNote: (Long) -> Unit,
    upPress: () -> Unit,
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is NoteDetailsSideEffect.NavigateToEditNote -> navigateToEditNote(sideEffect.noteId)
            is NoteDetailsSideEffect.NavigateBack -> upPress()
        }
    }

    NoteDetailsScreen(
        state = screenState,
        onBackPressed = viewModel::onBackPressed,
        onEditNote = viewModel::onEditNote,
    )
}


@Composable
fun NoteDetailsScreen(
    state: NoteDetailsScreenState,
    onBackPressed: () -> Unit,
    onEditNote: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(LocalAppColors.current.primaryContainerLight)
        ) {
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .imePadding()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = state.title,
                    textAlign = TextAlign.Center,
                    style = NotesKMPTheme.typography.headingXLarge,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = getStringValue(state.category.titleResId, state.category.title),
                    textAlign = TextAlign.Center,
                    style = NotesKMPTheme.typography.headingSmall,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = state.description,
                    style = NotesKMPTheme.typography.bodyLarge,
                )
            }
        }
        NoteDetailsTopAppBar(
            onBackPressed = onBackPressed,
            onEditNote = { onEditNote() },
        )
    }
}

@Composable
private fun NoteDetailsTopAppBar(
    onBackPressed: () -> Unit,
    onEditNote: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            onAction = onBackPressed,
            icon = Icons.AutoMirrored.Rounded.ArrowBack,
            iconContentResId = Res.string.navigate_back
        )
        Spacer(modifier = Modifier.weight(1f))
        ActionButton(
            onAction = onEditNote,
            icon = Icons.Rounded.Edit,
            iconContentResId = Res.string.button_edit
        )
    }
}
