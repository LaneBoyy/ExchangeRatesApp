package com.laneboy.exchangeratesapp.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    filtersScreenContent: @Composable (String?) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Home.route) {
            homeScreenContent()
        }
        composable(
            route = Screen.Filters.route,
            arguments = listOf(
                navArgument(Screen.KEY_CURRENCIES_SORT_TYPE) {
                    type = NavType.StringType
                }
            )
        ) {
            val sortType = it.arguments?.getString(Screen.KEY_CURRENCIES_SORT_TYPE)
            filtersScreenContent(sortType)
        }
    }
}
