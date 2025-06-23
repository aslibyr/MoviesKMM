package com.example.moviekmm.util

import com.example.moviekmm.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getSharedModules(),
        )
    }
}