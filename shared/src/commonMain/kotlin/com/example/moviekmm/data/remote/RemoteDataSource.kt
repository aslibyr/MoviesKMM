package com.example.moviekmm.data.remote

import com.example.moviekmm.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val service: Service,
    private val dispatcher: Dispatcher
) {

    suspend fun getMovies(page: Int = 1) = withContext(dispatcher.io) { service.getMovies(page) }

    suspend fun getMovieDetails(movieId: Int) =
        withContext(dispatcher.io) { service.getMovieDetails(movieId) }
}