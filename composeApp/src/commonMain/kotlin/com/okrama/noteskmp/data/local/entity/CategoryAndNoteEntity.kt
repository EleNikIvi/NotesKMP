package com.okrama.noteskmp.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation
import com.okrama.noteskmp.model.Category
import com.okrama.noteskmp.model.Note

@Entity(
    tableName = "category_note",
    primaryKeys = ["categoryId", "noteId"],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Note::class,
            parentColumns = ["noteId"],
            childColumns = ["noteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("noteId")
    ],
)
data class CategoryAndNoteEntity(
    val categoryId: Long,
    val noteId: Long,
)

data class NoteWithCategory(
    @Embedded
    val note: Note,

    @Relation(
        parentColumn = "noteId",
        entity = Category::class,
        entityColumn = "categoryId",
        associateBy = Junction(
            value = CategoryAndNoteEntity::class,
            parentColumn = "noteId",
            entityColumn = "categoryId"
        )
    )
    var category: Category?
)

