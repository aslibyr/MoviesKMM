package com.example.moviekmm.domain.model


data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)
