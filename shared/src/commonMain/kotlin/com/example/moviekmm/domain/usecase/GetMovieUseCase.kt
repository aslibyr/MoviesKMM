package com.example.moviekmm.domain.usecase

import com.example.moviekmm.domain.model.MovieModel
import com.example.moviekmm.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieUseCase : KoinComponent {
    private val movieRepository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(movieId: Int): MovieModel {
        return movieRepository.getMovieDetails(movieId)
    }
}