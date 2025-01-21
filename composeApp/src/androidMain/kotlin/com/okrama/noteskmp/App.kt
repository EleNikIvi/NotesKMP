package com.okrama.noteskmp

import android.app.Application
import com.okrama.noteskmp.ui.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@Application)
        }
    }
}