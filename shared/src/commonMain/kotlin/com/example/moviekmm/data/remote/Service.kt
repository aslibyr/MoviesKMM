package com.example.moviekmm.data.remote

import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class Service : KtorApi() {
    suspend fun getMovies() = client.get {
        pathUrl("movie/popular")
        parameter("page", 1)
    }

    suspend fun getMovieDetails(movieId: Int) = client.get {
        pathUrl("movie/$movieId")
    }
}