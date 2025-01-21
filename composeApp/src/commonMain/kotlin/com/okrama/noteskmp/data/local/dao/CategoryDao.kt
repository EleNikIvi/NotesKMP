package com.okrama.noteskmp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.CategoryWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category ORDER BY categoryId ASC")
    fun getCategoriesFlow(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCategory(category: Category): Long

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    suspend fun getCategoryWithNotes(categoryId: Long): CategoryWithNotes?
}