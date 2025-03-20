package com.laneboy.exchangeratesapp.presentation.screens.currencies

import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import javax.inject.Inject

class CurrenciesConverter @Inject constructor() {

    fun mapToDomain(uiModel: ExchangeRate): ExchangeRateDomain {
        return ExchangeRateDomain(
            baseCurrency = uiModel.baseCurrency,
            quotedCurrency = uiModel.quotedCurrency,
            cost = uiModel.cost
        )
    }

    fun mapToUi(
        list: List<ExchangeRateDomain>,
        favouritesList: List<ExchangeRateDomain>
    ): List<ExchangeRate> {
        val result = list.map { rate ->
            ExchangeRate(
                baseCurrency = rate.baseCurrency,
                quotedCurrency = rate.quotedCurrency,
                cost = rate.cost,
                isFavorite = favouritesList.any {
                    rate.baseCurrency == it.baseCurrency && rate.quotedCurrency == it.quotedCurrency
                }
            )
        }
        return result
    }

    fun getSortType(value: String): CurrenciesSortType {
        return CurrenciesSortType.entries.find { it.name == value } ?: CurrenciesSortType.CODE_AZ
    }
}