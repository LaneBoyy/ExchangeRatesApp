package com.laneboy.exchangeratesapp.presentation.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.domain.usecases.AddFavoriteExchangeUseCase
import com.laneboy.exchangeratesapp.domain.usecases.DeleteFavoriteExchangeUseCase
import com.laneboy.exchangeratesapp.domain.usecases.GetExchangeRatesUseCase
import com.laneboy.exchangeratesapp.domain.usecases.GetFavoriteRatesUseCase
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getFavoriteRatesUseCase: GetFavoriteRatesUseCase,
    private val addFavoriteExchangeUseCase: AddFavoriteExchangeUseCase,
    private val deleteFavoriteExchangeUseCase: DeleteFavoriteExchangeUseCase,
    private val currenciesConverter: CurrenciesConverter
) : ViewModel() {

    private val _state: MutableStateFlow<CurrenciesViewState> =
        MutableStateFlow(CurrenciesViewState())
    val state = _state.asStateFlow()

    init {
        getBaseCurrencies()
    }

    private fun getBaseCurrencies() {
        val baseCurrencies = BaseCurrency.entries.toList()
        _state.value = _state.value.copy(baseCurrencies = baseCurrencies)
    }

    fun initScreen(sortTypeValue: String?) {
        if (sortTypeValue != null) {
            val sortType = currenciesConverter.getSortType(value = sortTypeValue)
            _state.value = _state.value.copy(selectedSort = sortType)
        }
        loadExchangeRates()
    }

    private fun loadExchangeRates() {
        val errorHandler = errorHandler {
            _state.value = _state.value.toError()
        }
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            val result = getExchangeRatesUseCase.invoke(_state.value.selectedCurrency)
            val favoriteRates = getFavoriteRatesUseCase.invoke()
            val exchangeRates = currenciesConverter.mapToUi(
                list = result,
                favouritesList = favoriteRates
            )
            _state.value = _state.value.toContent(exchangeRates = exchangeRates.sortedBySortType())
        }
    }

    fun setSelectBaseCurrencyAndUpdate(selectedCurrency: BaseCurrency) {
        _state.value = _state.value.copy(selectedCurrency = selectedCurrency)
        updateExchangeRates()
    }

    fun updateExchangeRates() {
        _state.value = _state.value.toUpdateList()
        loadExchangeRates()
    }

    fun updateAfterError() {
        _state.value = _state.value.toLoading()
        loadExchangeRates()
    }

    fun addOrDeleteFavoriteRate(exchangeRate: ExchangeRate) {
        viewModelScope.launch(Dispatchers.IO) {
            if (exchangeRate.isFavorite) {
                deleteFavoriteExchangeUseCase.invoke(
                    exchangeRate.baseCurrency, exchangeRate.quotedCurrency
                )
            } else {
                addFavoriteExchangeUseCase.invoke(
                    currenciesConverter.mapToDomain(exchangeRate)
                )
            }
            updateFavoriteState(exchangeRate)
        }
    }

    private fun updateFavoriteState(exchangeRate: ExchangeRate) {
        val newList = _state.value.exchangeRates.map { item ->
            if (item.baseCurrency == exchangeRate.baseCurrency && item.quotedCurrency == exchangeRate.quotedCurrency) {
                item.copy(isFavorite = !item.isFavorite)
            } else item
        }
        _state.value = _state.value.copy(exchangeRates = newList)
    }

    private fun List<ExchangeRate>.sortedBySortType(): List<ExchangeRate> {
        return when (_state.value.selectedSort) {
            CurrenciesSortType.CODE_AZ -> {
                sortedBy { it.quotedCurrency }
            }

            CurrenciesSortType.CODE_ZA -> {
                sortedByDescending { it.quotedCurrency }
            }

            CurrenciesSortType.QUOTE_ASC -> {
                sortedBy { it.cost }
            }

            CurrenciesSortType.QUOTE_DESC -> {
                sortedByDescending { it.cost }
            }
        }
    }

    private inline fun errorHandler(
        crossinline onError: (Throwable) -> Unit = {}
    ): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }
}