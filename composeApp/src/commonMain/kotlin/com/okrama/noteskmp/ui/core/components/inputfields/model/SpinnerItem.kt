package com.okrama.noteskmp.ui.core.components.inputfields.model

import androidx.compose.runtime.Immutable
import com.okrama.noteskmp.ui.core.CommonParcelable
import com.okrama.noteskmp.ui.core.CommonParcelize
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@CommonParcelize
data class SpinnerItem(
    val id: Long,
    val value: String = "",
): CommonParcelable

@CommonParcelize
@Immutable
data class DropdownField(
    val value: String = "",
    val spinnerItems: ImmutableList<SpinnerItem> = persistentListOf(),
) : CommonParcelable
