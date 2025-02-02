package com.okrama.noteskmp.domain.category

import com.okrama.noteskmp.data.local.entity.NoteWithCategory
import com.okrama.noteskmp.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryInteractor(
    private val categoryRepository: CategoryRepository,
) {
    fun getCategories(): Flow<List<Category>> = categoryRepository.getCategories()

    fun getCategoryForNote(noteId: Long): Flow<NoteWithCategory?> =
        categoryRepository.getCategoryForNote(noteId = noteId)

    suspend fun addCategory(
        title: String,
    ): Long = categoryRepository.addCategory(
        category = Category(
            title = title,
        )
    )
}