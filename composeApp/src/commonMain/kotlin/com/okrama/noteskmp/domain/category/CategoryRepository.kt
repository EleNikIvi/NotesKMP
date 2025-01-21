package com.okrama.noteskmp.domain.category

import com.okrama.noteskmp.data.local.entity.NoteWithCategory
import com.okrama.noteskmp.model.Category
import kotlinx.coroutines.flow.Flow


interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun getCategoryForNote(noteId: Long): Flow<NoteWithCategory?>
    suspend fun addCategory(category: Category): Long
}