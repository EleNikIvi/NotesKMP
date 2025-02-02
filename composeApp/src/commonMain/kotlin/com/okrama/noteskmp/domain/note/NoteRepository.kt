package com.okrama.noteskmp.domain.note

import com.okrama.noteskmp.model.CategoryWithNotes
import com.okrama.noteskmp.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotesFlow(): Flow<List<Note>>
    fun getNoteFlow(noteId: Long): Flow<Note>
    suspend fun getNotesBy(categoryId: Long): CategoryWithNotes?
    suspend fun addNote(note: Note, categoryId: Long): Long
    suspend fun updateNote(note: Note, categoryId: Long): Long
    suspend fun deleteNote(noteId: Long)
}