package com.okrama.noteskmp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.okrama.noteskmp.data.local.entity.CategoryAndNoteEntity

@Dao
interface CategoryAndNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(categoryAndNoteEntity: CategoryAndNoteEntity)
}