package com.laneboy.exchangeratesapp.data.repository

import com.laneboy.exchangeratesapp.data.mapper.ExchangeRatesMapper
import com.laneboy.exchangeratesapp.data.network.ApiService
import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain
import com.laneboy.exchangeratesapp.domain.repository.ExchangeRatesApiRepository
import javax.inject.Inject

class ExchangeRatesApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val exchangeRatesMapper: ExchangeRatesMapper
) : ExchangeRatesApiRepository {

    override suspend fun getExchangeRates(baseCurrency: BaseCurrency): List<ExchangeRateDomain> {
        val result = apiService.getExchangeRates(baseCurrency.name)
        return exchangeRatesMapper.mapToDomain(result)
    }
}