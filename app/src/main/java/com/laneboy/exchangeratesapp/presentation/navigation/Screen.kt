package com.laneboy.exchangeratesapp.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen(ROUTE_HOME)
    data object Currencies : Screen(ROUTE_CURRENCIES)
    data object Favourites : Screen(ROUTE_FAVOURITES)

    data object Filters : Screen(ROUTE_FILTERS) {

        const val SORT_RESULT_KEY = "sort_key"
        private const val ROUTE_FOR_ARGS = "filters"

        fun getRouteWithArgs(sortType: String): String {
            return "$ROUTE_FOR_ARGS/${sortType}"
        }
    }

    companion object {

        const val KEY_CURRENCIES_SORT_TYPE = "sort_type"

        const val ROUTE_CURRENCIES = "currencies"
        const val ROUTE_FAVOURITES = "favourites"
        const val ROUTE_FILTERS = "filters/{$KEY_CURRENCIES_SORT_TYPE}"
        const val ROUTE_HOME = "home"
    }
}
