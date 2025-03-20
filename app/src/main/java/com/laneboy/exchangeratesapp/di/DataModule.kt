package com.laneboy.exchangeratesapp.di

import android.app.Application
import com.laneboy.exchangeratesapp.data.database.FavoriteRatesDao
import com.laneboy.exchangeratesapp.data.database.FavoriteRatesDatabase
import com.laneboy.exchangeratesapp.data.network.ApiFactory
import com.laneboy.exchangeratesapp.data.network.ApiService
import com.laneboy.exchangeratesapp.data.repository.ExchangeRatesApiRepositoryImpl
import com.laneboy.exchangeratesapp.data.repository.FavoriteRatesDatabaseRepositoryImpl
import com.laneboy.exchangeratesapp.domain.repository.ExchangeRatesApiRepository
import com.laneboy.exchangeratesapp.domain.repository.FavoriteRatesDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindExchangeRatesApiRepository(impl: ExchangeRatesApiRepositoryImpl): ExchangeRatesApiRepository

    @[ApplicationScope Binds]
    fun bindFavoriteRatesDatabaseRepository(impl: FavoriteRatesDatabaseRepositoryImpl): FavoriteRatesDatabaseRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideFavoriteRatesDao(application: Application): FavoriteRatesDao {
            return FavoriteRatesDatabase.getInstance(application).favoriteRatesDao()
        }

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
