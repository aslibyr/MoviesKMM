package com.example.moviekmm.android.screens.home

import com.example.moviekmm.domain.model.MovieModel

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<MovieModel> = emptyList(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false,
)
