package com.example.moviekmm.android.common.navigation

import kotlinx.serialization.Serializable


@Serializable
object HomeScreenRoute


@Serializable
data class MovieDetailRoute(val movieId: Int)

