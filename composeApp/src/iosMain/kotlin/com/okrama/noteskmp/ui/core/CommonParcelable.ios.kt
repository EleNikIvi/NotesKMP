package com.okrama.noteskmp.ui.core

actual interface CommonParcelable

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
actual annotation class IgnoredOnParcel actual constructor()