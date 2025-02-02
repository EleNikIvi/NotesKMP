package com.okrama.noteskmp.data.repository

import com.okrama.noteskmp.data.local.DatabaseUtils
import com.okrama.noteskmp.data.local.NotesKMPDatabase
import com.okrama.noteskmp.data.local.dao.CategoryAndNoteDao
import com.okrama.noteskmp.data.local.dao.CategoryDao
import com.okrama.noteskmp.data.local.dao.NoteDao
import com.okrama.noteskmp.data.local.entity.CategoryAndNoteEntity
import com.okrama.noteskmp.domain.note.NoteRepository
import com.okrama.noteskmp.model.CategoryWithNotes
import com.okrama.noteskmp.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    database: NotesKMPDatabase,
) : NoteRepository {
    private val noteDao: NoteDao = database.noteDao
    private val categoryDao: CategoryDao = database.categoryDao
    private val categoryAndNoteDao: CategoryAndNoteDao = database.categoryAndNoteDao

    override fun getAllNotesFlow(): Flow<List<Note>> = noteDao.getAllNotesFlow()

    override fun getNoteFlow(noteId: Long): Flow<Note> = noteDao.getNoteFlow(id = noteId)

    override suspend fun getNotesBy(categoryId: Long): CategoryWithNotes? =
        categoryDao.getCategoryWithNotes(categoryId = categoryId)

    override suspend fun addNote(
        note: Note,
        categoryId: Long,
    ) = insertOrUpdateNote(note = note, categoryId = categoryId)

    override suspend fun updateNote(
        note: Note,
        categoryId: Long,
    ) = insertOrUpdateNote(note = note, categoryId = categoryId)

    private suspend fun insertOrUpdateNote(
        note: Note,
        categoryId: Long,
    ): Long {
        var noteId = -1L
        DatabaseUtils.safeLaunch {
            noteId = noteDao.insertOrUpdateNote(note = note)
            DatabaseUtils.safeLaunch {
                categoryAndNoteDao.insertOrUpdate(
                    CategoryAndNoteEntity(
                        categoryId = categoryId,
                        noteId = noteId
                    )
                )
            }
        }
        return noteId
    }

    override suspend fun deleteNote(noteId: Long) = noteDao.deleteNote(noteId = noteId)
}