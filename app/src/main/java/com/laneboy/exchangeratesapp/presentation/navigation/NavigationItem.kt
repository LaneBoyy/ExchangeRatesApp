package com.laneboy.exchangeratesapp.presentation.navigation

import com.laneboy.exchangeratesapp.R

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val iconResId: Int
) {
    data object Currencies : NavigationItem(
        screen = Screen.Currencies,
        titleResId = R.string.common_currencies,
        iconResId = R.drawable.ic_currencies
    )

    data object Favourites : NavigationItem(
        screen = Screen.Favourites,
        titleResId = R.string.common_favorites,
        iconResId = R.drawable.ic_favorites
    )
}