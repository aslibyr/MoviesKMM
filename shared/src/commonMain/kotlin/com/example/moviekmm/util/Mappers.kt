package com.example.moviekmm.util

import com.example.moviekmm.data.remote.MovieRemote

internal fun MovieRemote.toMovie() = com.example.moviekmm.domain.model.Movie(
    id = id,
    title = title,
    description = overview,
    imageUrl = getImageUrl(posterPath),
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

private fun getImageUrl(posterPath: String?) = "https://image.tmdb.org/t/p/w500$posterPath"