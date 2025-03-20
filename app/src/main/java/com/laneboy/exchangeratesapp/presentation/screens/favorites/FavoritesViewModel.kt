package com.laneboy.exchangeratesapp.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laneboy.exchangeratesapp.domain.usecases.DeleteFavoriteExchangeUseCase
import com.laneboy.exchangeratesapp.domain.usecases.GetFavoriteRatesUseCase
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoriteRatesUseCase: GetFavoriteRatesUseCase,
    private val deleteFavoriteExchangeUseCase: DeleteFavoriteExchangeUseCase,
    private val favoritesConverter: FavoritesConverter
) : ViewModel() {

    private val _state: MutableStateFlow<FavoritesViewState> =
        MutableStateFlow(FavoritesViewState())
    val state = _state.asStateFlow()

    fun getFavoriteRates() {
        viewModelScope.launch(Dispatchers.IO) {
            updateStateList()
        }
    }

    fun deleteFavoriteRate(exchangeRate: ExchangeRate) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteExchangeUseCase.invoke(
                exchangeRate.baseCurrency, exchangeRate.quotedCurrency
            )
            updateStateList()
        }
    }

    private suspend fun updateStateList() {
        val result = getFavoriteRatesUseCase.invoke()
        _state.value = if (result.isEmpty()) {
            _state.value.toEmpty()
        } else {
            _state.value.toContent(
                favoriteExchangeRates = favoritesConverter.mapToUIModel(result)
            )
        }
    }
}