package com.laneboy.exchangeratesapp.presentation.screens.currencies

import androidx.compose.runtime.Immutable
import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate

@Immutable
data class CurrenciesViewState(
    val screenState: CurrenciesScreenState = CurrenciesScreenState.LOADING,
    val selectedCurrency: BaseCurrency = BaseCurrency.EUR,
    val selectedSort: CurrenciesSortType = CurrenciesSortType.CODE_AZ,
    val exchangeRates: List<ExchangeRate> = listOf(),
    val baseCurrencies: List<BaseCurrency> = listOf(),
    val isListRefreshing: Boolean = false
) {

    fun toError() = copy(screenState = CurrenciesScreenState.ERROR)

    fun toLoading() = copy(screenState = CurrenciesScreenState.LOADING)

    fun toUpdateList() = copy(isListRefreshing = true)

    fun toContent(exchangeRates: List<ExchangeRate>) = copy(
        screenState = CurrenciesScreenState.CONTENT,
        exchangeRates = exchangeRates,
        isListRefreshing = false
    )

    enum class CurrenciesScreenState {
        LOADING, ERROR, CONTENT
    }
}