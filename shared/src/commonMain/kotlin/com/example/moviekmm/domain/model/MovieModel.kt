package com.example.moviekmm.domain.model

data class MovieModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String? = "",
    val backdropPath: String? = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)
