package com.laneboy.exchangeratesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.laneboy.exchangeratesapp.presentation.navigation.AppNavGraph
import com.laneboy.exchangeratesapp.presentation.navigation.Screen.Filters.SORT_RESULT_KEY
import com.laneboy.exchangeratesapp.presentation.navigation.rememberNavigationState
import com.laneboy.exchangeratesapp.presentation.screens.filters.FiltersScreen
import com.laneboy.exchangeratesapp.presentation.theme.ExchangeRatesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val navHostController = navigationState.navHostController
    AppNavGraph(
        navHostController = navHostController,
        homeScreenContent = {
            HomeScreen(
                navHostController = navHostController
            )
        },
        filtersScreenContent = { sortType ->
            FiltersScreen(
                currenciesSortTypeName = sortType,
                onNavigationBack = { sortTypeResult ->
                    if (sortType != null) {
                        navHostController
                            .previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(SORT_RESULT_KEY, sortTypeResult)
                    }
                    navHostController.popBackStack()
                }
            )
        }
    )
}