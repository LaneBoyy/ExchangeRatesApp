package com.laneboy.exchangeratesapp.data.network

import com.laneboy.exchangeratesapp.data.model.ExchangeRatesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base") base: String
    ): ExchangeRatesResponseDto
}