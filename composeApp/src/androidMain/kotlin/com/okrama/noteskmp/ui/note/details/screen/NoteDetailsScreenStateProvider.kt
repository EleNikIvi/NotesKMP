package com.okrama.noteskmp.ui.note.details.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.okrama.noteskmp.ui.core.LoremIpsum
import com.okrama.noteskmp.ui.note.details.NoteDetailsScreenState


class NoteDetailsScreenStateProvider : PreviewParameterProvider<NoteDetailsScreenState> {
    override val values: Sequence<NoteDetailsScreenState> = sequenceOf(
        NoteDetailsScreenState(
            title = "Title of new note",
            description = LoremIpsum.PARAGRAPH,
        )
    )
}