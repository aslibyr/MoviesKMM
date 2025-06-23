package com.example.moviekmm.di

import com.example.moviekmm.data.remote.RemoteDataSource
import com.example.moviekmm.data.remote.Service
import com.example.moviekmm.data.repository.MovieRepositoryImpl
import com.example.moviekmm.domain.repository.MovieRepository
import com.example.moviekmm.domain.usecase.GetMovieUseCase
import com.example.moviekmm.domain.usecase.GetMoviesUseCase
import com.example.moviekmm.util.provideDispatcher
import org.koin.dsl.module

//paylaşımlı modüllerde single yerine factory kullanılır çünkü her modülün kendi instance'ı olmalı
private val dataModule = module {
    factory {
        RemoteDataSource(
            service = get(),
            dispatcher = get()
        )
    }
    factory { Service() }
}

private val domainModule = module {
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
private val utilModule = module {
    factory { provideDispatcher() }
}

private val sharedModules = listOf(
    dataModule,
    domainModule,
    utilModule
)

fun getSharedModules() = sharedModules