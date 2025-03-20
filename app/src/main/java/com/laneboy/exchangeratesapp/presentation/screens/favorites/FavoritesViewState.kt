package com.laneboy.exchangeratesapp.presentation.screens.favorites

import androidx.compose.runtime.Immutable
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate

@Immutable
data class FavoritesViewState(
    val screenState: FavoritesScreenState = FavoritesScreenState.LOADING,
    val favoriteExchangeRates: List<ExchangeRate> = listOf()
) {

    fun toEmpty() = copy(screenState = FavoritesScreenState.EMPTY)

    fun toContent(favoriteExchangeRates: List<ExchangeRate>) = copy(
        screenState = FavoritesScreenState.CONTENT,
        favoriteExchangeRates = favoriteExchangeRates,
    )

    enum class FavoritesScreenState {
        LOADING, EMPTY, CONTENT
    }
}