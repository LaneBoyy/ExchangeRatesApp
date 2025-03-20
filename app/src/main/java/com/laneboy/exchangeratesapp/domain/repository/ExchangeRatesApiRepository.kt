package com.laneboy.exchangeratesapp.domain.repository

import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.domain.model.ExchangeRateDomain

interface ExchangeRatesApiRepository {

   suspend fun getExchangeRates(baseCurrency: BaseCurrency): List<ExchangeRateDomain>
}