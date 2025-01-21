package com.okrama.noteskmp.ui.core.di

import com.okrama.noteskmp.data.di.dataModule
import com.okrama.noteskmp.data.di.platformModule
import com.okrama.noteskmp.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            viewModelModule,
            domainModule,
            dataModule,
            platformModule(),
        )
    }
}