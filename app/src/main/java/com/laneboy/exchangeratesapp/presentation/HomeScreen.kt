package com.laneboy.exchangeratesapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.laneboy.exchangeratesapp.presentation.navigation.BottomBarNavGraph
import com.laneboy.exchangeratesapp.presentation.navigation.NavigationItem
import com.laneboy.exchangeratesapp.presentation.navigation.NavigationState
import com.laneboy.exchangeratesapp.presentation.navigation.Screen
import com.laneboy.exchangeratesapp.presentation.navigation.Screen.Filters.SORT_RESULT_KEY
import com.laneboy.exchangeratesapp.presentation.navigation.rememberNavigationState
import com.laneboy.exchangeratesapp.presentation.screens.currencies.CurrenciesScreen
import com.laneboy.exchangeratesapp.presentation.screens.favorites.FavoritesScreen
import com.laneboy.exchangeratesapp.presentation.theme.LightPrimary
import com.laneboy.exchangeratesapp.presentation.theme.Outline
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.Secondary
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text12Medium
import com.laneboy.exchangeratesapp.presentation.theme.text12Semibold

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationState)
        },
    ) { bottomPaddingValues ->
        BottomBarNavGraph(
            navHostController = navigationState.navHostController,
            currenciesScreenContent = {
                val sortTypeResult = navHostController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>(SORT_RESULT_KEY)
                CurrenciesScreen(
                    paddingValues = bottomPaddingValues,
                    sortType = sortTypeResult,
                    onFiltersClick = {
                        navHostController.navigate(Screen.Filters.getRouteWithArgs(it))
                    }
                )
            },
            favoritesScreenContent = {
                FavoritesScreen(paddingValues = bottomPaddingValues)
            }
        )
    }
}

@Composable
private fun BottomNavigationBar(navigationState: NavigationState) {
    Column {
        HorizontalDivider(color = Outline)
        NavigationBar(containerColor = White) {
            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRout = navBackStackEntry?.destination?.route

            val items = listOf(NavigationItem.Currencies, NavigationItem.Favourites)
            items.forEach { item ->
                val isSelected = currentRout == item.screen.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { navigationState.navigateTo(item.screen.route) },
                    icon = {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            painter = painterResource(item.iconResId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.titleResId),
                            style = if (isSelected) text12Semibold else text12Medium.copy(color = Secondary)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Primary,
                        unselectedIconColor = Secondary,
                        indicatorColor = LightPrimary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navHostController = NavHostController(LocalContext.current)
    )
}
