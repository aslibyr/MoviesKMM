package com.example.moviekmm.data.repository

import com.example.moviekmm.data.remote.RemoteDataSource
import com.example.moviekmm.data.remote.Service
import com.example.moviekmm.domain.model.Movie
import com.example.moviekmm.domain.repository.MovieRepository
import com.example.moviekmm.util.toMovie

internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {

    private val movieService = Service()

    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page).results.map { it.toMovie() }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return movieService.getMovieDetails(movieId).toMovie()

    }

    // Uncomment if search functionality is implemented
    // override suspend fun searchMovies(query: String, page: Int): Result<List<Movie>> {
    //     return movieService.searchMovies(query, page).map { response ->
    //         response.results.map { it.toDomainModel() }
    //     }
    // }
}