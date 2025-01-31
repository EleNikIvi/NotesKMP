package com.okrama.noteskmp.ui.core.di

import androidx.lifecycle.SavedStateHandle
import com.okrama.noteskmp.ui.category.addcategory.AddCategoryViewModel
import com.okrama.noteskmp.ui.note.addnote.AddNoteViewModel
import com.okrama.noteskmp.ui.note.details.NoteDetailsViewModel
import com.okrama.noteskmp.ui.note.notes.NotesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<NotesViewModel> { (handle: SavedStateHandle) ->
        NotesViewModel( handle, get(), get())
    }
    viewModel<AddCategoryViewModel> { (handle: SavedStateHandle) ->
        AddCategoryViewModel(get(), handle)
    }
    viewModel<AddNoteViewModel> { (handle: SavedStateHandle) ->
        AddNoteViewModel(get(), get(), handle)
    }
    viewModel<NoteDetailsViewModel> { (handle: SavedStateHandle) ->
        NoteDetailsViewModel(get(), get(), handle)
    }
}