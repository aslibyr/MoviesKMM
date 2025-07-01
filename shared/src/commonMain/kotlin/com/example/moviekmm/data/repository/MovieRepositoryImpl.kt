package com.example.moviekmm.data.repository

import com.example.moviekmm.data.remote.RemoteDataSource
import com.example.moviekmm.data.remote.Service
import com.example.moviekmm.domain.mapper.toMovie
import com.example.moviekmm.domain.model.MovieModel
import com.example.moviekmm.domain.repository.MovieRepository

internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {

    private val movieService = Service()

    override suspend fun getMovies(page: Int): List<MovieModel> {
        return remoteDataSource.getMovies(page).results.map { it.toMovie() }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieModel {
        return movieService.getMovieDetails(movieId).toMovie()

    }

    // Uncomment if search functionality is implemented
    // override suspend fun searchMovies(query: String, page: Int): Result<List<Movie>> {
    //     return movieService.searchMovies(query, page).map { response ->
    //         response.results.map { it.toDomainModel() }
    //     }
    // }
}