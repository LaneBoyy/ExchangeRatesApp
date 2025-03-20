package com.laneboy.exchangeratesapp.presentation.screens.favorites

import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import javax.inject.Inject

class FavoritesConverter @Inject constructor() {

    fun mapToUIModel(domainModel: List<ExchangeRateDomain>): List<ExchangeRate> {
        val result = domainModel.map { rate ->
            ExchangeRate(
                baseCurrency = rate.baseCurrency,
                quotedCurrency = rate.quotedCurrency,
                cost = rate.cost,
                isFavorite = true
            )
        }
        return result
    }
}