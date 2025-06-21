package com.example.moviekmm.data.remote

import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponse(
    val page: Int,
    val results: List<MovieRemote>,
    val totalPages: Int,
    val totalResults: Int
)