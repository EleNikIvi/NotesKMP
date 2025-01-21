package com.okrama.noteskmp.ui.core.components.filterrail.model

import androidx.compose.runtime.Immutable
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize

@CommonParcelize
@Immutable
data class FilterRailItem(
    val id: Long,
    val value: String = "",
) : CommonParcelable

val FILTER_ALL = FilterRailItem(
    id = 0,
    value = "ALL",
)