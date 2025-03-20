package com.laneboy.exchangeratesapp.domain.usecases

import com.laneboy.exchangeratesapp.domain.repository.FavoriteRatesDatabaseRepository
import javax.inject.Inject

class DeleteFavoriteExchangeUseCase @Inject constructor(
    private val repository: FavoriteRatesDatabaseRepository
) {

    suspend operator fun invoke(baseCurrency: String, quotedCurrency: String) {
        return repository.deleteFavoriteExchange(baseCurrency, quotedCurrency)
    }
}