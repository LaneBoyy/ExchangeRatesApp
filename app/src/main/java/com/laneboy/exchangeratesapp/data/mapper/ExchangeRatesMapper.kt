package com.laneboy.exchangeratesapp.data.mapper

import com.laneboy.exchangeratesapp.data.model.ExchangeRatesResponseDto
import com.laneboy.exchangeratesapp.data.model.FavoriteExchangeDbModel
import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import javax.inject.Inject

class ExchangeRatesMapper @Inject constructor() {

    fun mapToDbModel(domainModel: ExchangeRateDomain): FavoriteExchangeDbModel {
        return FavoriteExchangeDbModel(
            baseCurrency = domainModel.baseCurrency,
            quotedCurrency = domainModel.quotedCurrency,
            cost = domainModel.cost
        )
    }

    fun mapToDomain(responseDto: ExchangeRatesResponseDto): List<ExchangeRateDomain> {
        val result = responseDto.rates.map { rate ->
            ExchangeRateDomain(
                baseCurrency = responseDto.base,
                quotedCurrency = rate.key,
                cost = rate.value
            )
        }
        return result
    }

    fun mapToDomain(dbModel: FavoriteExchangeDbModel): ExchangeRateDomain {
        return ExchangeRateDomain(
            baseCurrency = dbModel.baseCurrency,
            quotedCurrency = dbModel.quotedCurrency,
            cost = dbModel.cost
        )
    }
}