package com.example.moviekmm.android.screens.detail

import com.example.moviekmm.domain.model.MovieModel

data class DetailScreenState(
    var loading: Boolean = false,
    var movie: MovieModel? = null,
    var errorMessage: String? = null
)
