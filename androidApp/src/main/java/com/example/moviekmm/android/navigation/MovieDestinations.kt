package com.example.moviekmm.android.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
    val title: String
    val routeWithArgs: String

}

object Home : Destination {
    override val route: String
        get() = "home"
    override val title: String
        get() = "Movies"
    override val routeWithArgs: String
        get() = route
}

object Detail : Destination {
    override val title: String
        get() = "Movie Details"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"

    val arguments = listOf(navArgument("movieId") {
        type = NavType.IntType
    })
}

val movieDestinations = listOf(Home, Detail)