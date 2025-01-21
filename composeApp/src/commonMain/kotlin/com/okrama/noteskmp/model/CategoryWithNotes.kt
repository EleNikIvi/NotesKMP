package com.okrama.noteskmp.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.okrama.noteskmp.data.local.entity.CategoryAndNoteEntity

data class CategoryWithNotes(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entity = Note::class,
        entityColumn = "noteId",
        associateBy = Junction(
            value = CategoryAndNoteEntity::class,
            parentColumn = "categoryId",
            entityColumn = "noteId"
        )
    )
    var notes: List<Note>
)
