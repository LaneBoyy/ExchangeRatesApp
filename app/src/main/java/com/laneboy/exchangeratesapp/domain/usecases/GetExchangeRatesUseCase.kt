package com.laneboy.exchangeratesapp.domain.usecases

import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.domain.repository.ExchangeRatesApiRepository
import com.laneboy.exchangeratesapp.domain.repository.FavoriteRatesDatabaseRepository
import javax.inject.Inject

class GetExchangeRatesUseCase @Inject constructor(
    private val ratesRepository: ExchangeRatesApiRepository,
    private val favouritesRepository: FavoriteRatesDatabaseRepository
) {

    suspend operator fun invoke(baseCurrency: BaseCurrency): List<ExchangeRateDomain> {
        val result = ratesRepository.getExchangeRates(baseCurrency)
        favouritesRepository.updateFavouriteExchanges(result)
        return result
    }
}