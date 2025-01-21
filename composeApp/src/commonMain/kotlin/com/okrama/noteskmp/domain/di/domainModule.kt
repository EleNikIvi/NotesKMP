package com.okrama.noteskmp.domain.di

import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.domain.note.NoteInteractor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::CategoryInteractor)
    singleOf(::NoteInteractor)
}