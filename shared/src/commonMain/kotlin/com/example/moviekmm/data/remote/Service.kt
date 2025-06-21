package com.example.moviekmm.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class Service : KtorApi() {
    suspend fun getMovies(page: Int = 1): MoviesResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", 1)
    }.body()

    suspend fun getMovieDetails(movieId: Int): MovieRemote = client.get {
        pathUrl("movie/$movieId")
    }.body()
}