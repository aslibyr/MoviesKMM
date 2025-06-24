package com.example.moviekmm.android.common.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviekmm.android.common.navigation.HomeScreenRoute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BottomBar(
    navController: NavController,
    isBottomBarVisible: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        BottomBarRoute(
            name = "Home",
            route = HomeScreenRoute, // String olarak route
            icon = Icons.Default.Home
        ),

        )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AnimatedVisibility(isBottomBarVisible) {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
                    .height(110.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(30.dp)),
            ) {
                items.forEach { item ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = selected,
                                enter = fadeIn() +
                                        expandVertically(),
                                exit = fadeOut() +
                                        shrinkVertically()
                            ) {
                                Text(
                                    text = item.name,
                                    fontSize = 10.sp,
                                    modifier = Modifier.animateContentSize()
                                )
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = item.name, Modifier.size(30.dp))
                        },
                        interactionSource = NoRippleInteractionSource,
                    )
                }
            }
        }
    }
}

private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}