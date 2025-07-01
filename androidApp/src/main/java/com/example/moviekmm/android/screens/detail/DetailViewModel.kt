package com.example.moviekmm.android.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviekmm.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    private val movieId: Int
) : ViewModel() {
    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val movie = getMovieUseCase(movieId = movieId)
                uiState.copy(loading = false, movie = movie)
            } catch (error: Throwable) {
                uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the movie"
                )
            }
        }
    }
}