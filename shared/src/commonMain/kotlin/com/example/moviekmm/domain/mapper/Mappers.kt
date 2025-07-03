package com.example.moviekmm.domain.mapper

import com.example.moviekmm.data.remote.MovieRemote

internal fun MovieRemote.toMovie() = com.example.moviekmm.domain.model.MovieModel(
    id = id,
    title = title,
    description = overview,
    imageUrl = getImageUrl(posterPath),
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

private fun getImageUrl(posterPath: String?): String {
    return posterPath?.let {
        "https://image.tmdb.org/t/p/w500$it"
    } ?: ""
}
