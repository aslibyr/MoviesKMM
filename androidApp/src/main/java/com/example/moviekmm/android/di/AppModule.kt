package com.example.moviekmm.android.di

import com.example.moviekmm.android.screens.detail.DetailViewModel
import com.example.moviekmm.android.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}