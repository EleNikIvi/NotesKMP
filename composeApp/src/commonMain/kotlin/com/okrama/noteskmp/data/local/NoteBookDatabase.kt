package com.okrama.noteskmp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.okrama.noteskmp.data.local.dao.CategoryAndNoteDao
import com.okrama.noteskmp.data.local.dao.CategoryDao
import com.okrama.noteskmp.data.local.dao.NoteDao
import com.okrama.noteskmp.data.local.entity.CategoryAndNoteEntity
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.Note

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NotesKMPDatabaseCtor : RoomDatabaseConstructor<NotesKMPDatabase>


@Database(
    entities = [
        Note::class,
        Category::class,
        CategoryAndNoteEntity::class,
    ],
    version = 1,
)
@ConstructedBy(NotesKMPDatabaseCtor::class)
abstract class NotesKMPDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val categoryDao: CategoryDao
    abstract val categoryAndNoteDao: CategoryAndNoteDao
}