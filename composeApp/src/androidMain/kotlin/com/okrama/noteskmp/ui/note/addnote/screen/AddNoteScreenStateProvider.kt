package com.okrama.noteskmp.ui.note.addnote.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.okrama.noteskmp.ui.core.LoremIpsum
import com.okrama.noteskmp.ui.note.addnote.AddNoteScreenState

class AddNoteScreenStateProvider : PreviewParameterProvider<AddNoteScreenState> {
    override val values: Sequence<AddNoteScreenState> = sequenceOf(
        AddNoteScreenState(
            title = "Title of new note",
            description = LoremIpsum.PARAGRAPH,
            canSave = true,
        )
    )
}