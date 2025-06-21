package com.example.moviekmm.domain.repository

import com.example.moviekmm.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
    //suspend fun searchMovies(query: String, page: Int): Result<List<com.example.moviekmm.domain.model.Movie>>

}