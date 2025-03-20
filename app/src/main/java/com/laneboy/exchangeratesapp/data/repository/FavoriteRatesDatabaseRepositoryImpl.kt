package com.laneboy.exchangeratesapp.data.repository

import com.laneboy.exchangeratesapp.data.mapper.ExchangeRatesMapper
import com.laneboy.exchangeratesapp.data.database.FavoriteRatesDao
import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.domain.repository.FavoriteRatesDatabaseRepository
import javax.inject.Inject

class FavoriteRatesDatabaseRepositoryImpl @Inject constructor(
    private val favoriteRatesDao: FavoriteRatesDao,
    private val exchangeRatesMapper: ExchangeRatesMapper
) : FavoriteRatesDatabaseRepository {

    override suspend fun getFavoriteRates(): List<ExchangeRateDomain> {
        val result = favoriteRatesDao.getFavoriteRates()
        return result.map { exchangeRatesMapper.mapToDomain(it) }
    }

    override suspend fun addFavoriteExchange(exchangeRateDomain: ExchangeRateDomain) {
        favoriteRatesDao.addFavoriteExchange(
            exchangeRatesMapper.mapToDbModel(exchangeRateDomain)
        )
    }

    override suspend fun deleteFavoriteExchange(
        baseCurrency: String,
        quotedCurrency: String
    ) {
        favoriteRatesDao.deleteFavoriteExchange(baseCurrency, quotedCurrency)
    }

    override suspend fun updateFavouriteExchanges(list: List<ExchangeRateDomain>) {
        val newDbList = list.map { exchangeRatesMapper.mapToDbModel(it) }
        favoriteRatesDao.updateFavouriteExchange(newDbList)
    }
}