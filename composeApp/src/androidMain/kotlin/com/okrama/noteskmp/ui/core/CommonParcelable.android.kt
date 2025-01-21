package com.okrama.noteskmp.ui.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
actual annotation class IgnoredOnParcel actual constructor()