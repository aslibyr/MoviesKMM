package com.example.moviekmm.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atilsamancioglu.kmmmovieapp.android.common.MovieAppBar
import com.example.moviekmm.android.navigation.Detail
import com.example.moviekmm.android.navigation.Home
import com.example.moviekmm.android.navigation.movieDestinations
import com.example.moviekmm.android.screens.home.HomeScreen
import com.example.moviekmm.android.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    val currentScreen = movieDestinations.find {
        backStackEntry.value?.destination?.route == it.route || backStackEntry.value?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                modifier = modifier,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home.routeWithArgs,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(uiState = homeViewModel.uiState,
                    loadNextMovies = {
                        homeViewModel.loadMovies(forceReload = it)
                    }, navigateToDetail = {
                        navController.navigate("${Detail.route}/${it.id}")
                    })
            }
            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
            }
        }

    }
}