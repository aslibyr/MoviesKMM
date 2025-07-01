package com.example.moviekmm.domain.repository

import com.example.moviekmm.domain.model.MovieModel

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<MovieModel>
    suspend fun getMovieDetails(movieId: Int): MovieModel
    //suspend fun searchMovies(query: String, page: Int): Result<List<com.example.moviekmm.domain.model.Movie>>

}