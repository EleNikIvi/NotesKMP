package com.okrama.noteskmp.ui.core


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class CommonParcelize()

expect interface CommonParcelable

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
expect annotation class IgnoredOnParcel()