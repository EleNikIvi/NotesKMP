package com.okrama.noteskmp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize

const val EMPTY_CATEGORY_ID = -1L

@CommonParcelize
@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0L,
    val title: String,
) : CommonParcelable