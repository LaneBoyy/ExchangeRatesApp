package com.laneboy.exchangeratesapp.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomBarNavGraph(
    navHostController: NavHostController,
    currenciesScreenContent: @Composable () -> Unit,
    favoritesScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Currencies.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Currencies.route) {
            currenciesScreenContent()
        }
        composable(Screen.Favourites.route) {
            favoritesScreenContent()
        }
    }
}