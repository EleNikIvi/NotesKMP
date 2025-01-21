package com.okrama.noteskmp.data.di

import com.okrama.noteskmp.data.local.NotesKMPDatabase
import com.okrama.noteskmp.data.local.getDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
    single<NotesKMPDatabase> { getDatabase(get()) }
}