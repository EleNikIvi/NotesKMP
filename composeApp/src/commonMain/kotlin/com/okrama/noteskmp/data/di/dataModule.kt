package com.okrama.noteskmp.data.di

import com.okrama.noteskmp.data.repository.CategoryRepositoryImpl
import com.okrama.noteskmp.data.repository.NoteRepositoryImpl
import com.okrama.noteskmp.domain.category.CategoryRepository
import com.okrama.noteskmp.domain.note.NoteRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val dataModule = module {
    single<NoteRepository> {
        NoteRepositoryImpl(
            database = get(),
        )
    }
    single<CategoryRepository> {
        CategoryRepositoryImpl(
            database = get(),
        )
    }
}