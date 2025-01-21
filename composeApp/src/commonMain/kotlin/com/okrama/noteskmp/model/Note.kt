package com.okrama.noteskmp.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize

const val EMPTY_NOTE_ID = -1L

@CommonParcelize
@Immutable
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0,
    val title: String,
    val description: String,
) : CommonParcelable
