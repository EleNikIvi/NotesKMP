package com.okrama.noteskmp

import com.okrama.noteskmp.data.local.entity.NoteWithCategory
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.CategoryWithNotes
import com.okrama.noteskmp.model.Note

val note1 = Note(1, "Note 1", "Description 1")
val note2 = Note(2, "Note 2", "Description 2")
val note3 = Note(3, "Note 3", "Description 3")
val notes = listOf(note1, note2, note3)

val category1 = Category(1, "Category 1")
val category2 = Category(2, "Category 2")
val categories = listOf(category1, category2)

val note2WithCategory1 = NoteWithCategory(note2, category1)
val categoryWithNotes = CategoryWithNotes(category2, listOf(note1, note3))
