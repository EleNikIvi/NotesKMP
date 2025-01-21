package com.okrama.noteskmp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.okrama.noteskmp.data.local.entity.NoteWithCategory
import com.okrama.noteskmp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY noteId ASC")
    fun getAllNotesFlow(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE noteId = :id")
    fun getNoteFlow(id: Long): Flow<Note>

    @Insert(onConflict = REPLACE)
    suspend fun insertOrUpdateNote(note: Note): Long

    @Query("DELETE FROM note WHERE noteId = :noteId")
    suspend fun deleteNote(noteId: Long)


    @Transaction
    @Query("SELECT * FROM note WHERE noteId = :noteId")
    fun getCategoryWithNoteFlow(noteId: Long): Flow<NoteWithCategory?>
}