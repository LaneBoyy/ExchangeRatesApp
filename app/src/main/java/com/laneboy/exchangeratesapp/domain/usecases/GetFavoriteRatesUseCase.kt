package com.laneboy.exchangeratesapp.domain.usecases

import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.domain.repository.FavoriteRatesDatabaseRepository
import javax.inject.Inject

class GetFavoriteRatesUseCase @Inject constructor(
    private val repository: FavoriteRatesDatabaseRepository
) {

    suspend operator fun invoke(): List<ExchangeRateDomain> {
        return repository.getFavoriteRates()
    }
}