package com.example.moviekmm.android

import android.app.Application
import com.example.moviekmm.android.di.appModule
import com.example.moviekmm.di.getSharedModules
import org.koin.core.context.startKoin

class Movie : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}
