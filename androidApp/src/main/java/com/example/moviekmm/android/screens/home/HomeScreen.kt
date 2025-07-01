package com.example.moviekmm.android.screens.home

import MovieListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviekmm.domain.model.MovieModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: PullToRefreshState = rememberPullToRefreshState(),
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
    navigateToDetail: (MovieModel) -> Unit
) {
    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        state = state,
        isRefreshing = uiState.loading,
        onRefresh = { loadNextMovies(true) }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(uiState.movies, key = { _, movie -> movie.id }) { index, movie ->
                MovieListItem(
                    movie = movie,
                    onClick = navigateToDetail,
                    modifier = Modifier.fillMaxSize()
                )
                if (index == uiState.movies.size - 1 && uiState.loadFinished.not()) {
                    LaunchedEffect(key1 = Unit, block = { loadNextMovies(false) })
                }
            }

            if (uiState.loading && uiState.movies.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = modifier.align(
                                Alignment.CenterVertically,
                            ),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}