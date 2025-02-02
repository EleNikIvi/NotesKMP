package com.okrama.noteskmp.data.repository

import com.okrama.noteskmp.data.local.DatabaseUtils
import com.okrama.noteskmp.data.local.NotesKMPDatabase
import com.okrama.noteskmp.data.local.dao.CategoryDao
import com.okrama.noteskmp.data.local.dao.NoteDao
import com.okrama.noteskmp.data.local.entity.NoteWithCategory
import com.okrama.noteskmp.domain.category.CategoryRepository
import com.okrama.noteskmp.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    database: NotesKMPDatabase,
) : CategoryRepository {
    private val categoryDao: CategoryDao = database.categoryDao
    private val noteDao: NoteDao = database.noteDao

    override fun getCategories(): Flow<List<Category>> = categoryDao.getCategoriesFlow()

    override fun getCategoryForNote(noteId: Long): Flow<NoteWithCategory?> =
        noteDao.getCategoryWithNoteFlow(noteId = noteId)

    override suspend fun addCategory(category: Category): Long =
        insertOrUpdateCategory(category = category)

    private suspend fun insertOrUpdateCategory(category: Category): Long {
        var categoryId = -1L
        DatabaseUtils.safeLaunch {
            categoryId = categoryDao.insertOrUpdateCategory(category = category)
        }
        return categoryId
    }
}