package com.okrama.noteskmp.domain.note

import com.okrama.noteskmp.model.CategoryWithNotes
import com.okrama.noteskmp.model.Note
import kotlinx.coroutines.flow.Flow

class NoteInteractor(
    private val noteRepository: NoteRepository,
) {
    fun getAllNotesFlow(): Flow<List<Note>> = noteRepository.getAllNotesFlow()

    suspend fun getNotesBy(categoryId: Long): CategoryWithNotes? =
        noteRepository.getNotesBy(categoryId = categoryId)

    fun getNoteFlow(noteId: Long): Flow<Note> = noteRepository.getNoteFlow(noteId = noteId)

    suspend fun addNote(
        title: String,
        description: String,
        categoryId: Long,
    ): Long = noteRepository.addNote(
        note = Note(
            title = title,
            description = description,
        ),
        categoryId = categoryId,
    )

    suspend fun updateNote(
        id: Long,
        title: String,
        description: String,
        categoryId: Long,
    ): Long {
        val noteId = noteRepository.updateNote(
            note = Note(
                noteId = id,
                title = title,
                description = description,
            ),
            categoryId = categoryId,
        )
        return noteId
    }

    suspend fun deleteNote(noteId: Long) = noteRepository.deleteNote(noteId = noteId)
}