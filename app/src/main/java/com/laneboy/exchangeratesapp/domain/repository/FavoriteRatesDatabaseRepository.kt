package com.laneboy.exchangeratesapp.domain.repository

import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain

interface FavoriteRatesDatabaseRepository {

    suspend fun getFavoriteRates() : List<ExchangeRateDomain>

    suspend fun addFavoriteExchange(exchangeRateDomain: ExchangeRateDomain)

    suspend fun deleteFavoriteExchange(baseCurrency: String, quotedCurrency: String)

    suspend fun updateFavouriteExchanges(list:  List<ExchangeRateDomain>)
}