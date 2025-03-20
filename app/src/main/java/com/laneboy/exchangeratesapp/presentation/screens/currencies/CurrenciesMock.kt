package com.laneboy.exchangeratesapp.presentation.screens.currencies

import com.laneboy.exchangeratesapp.presentation.common.model.exchangeRateFavoriteMock
import com.laneboy.exchangeratesapp.presentation.common.model.exchangeRateMock

val currenciesViewStateMock = CurrenciesViewState(
    screenState = CurrenciesViewState.CurrenciesScreenState.CONTENT,
    exchangeRates = listOf(
        exchangeRateMock,
        exchangeRateMock.copy(quotedCurrency = "USD"),
        exchangeRateFavoriteMock.copy(quotedCurrency = "BYN")
    )
)