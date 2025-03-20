package com.laneboy.exchangeratesapp.di

import androidx.lifecycle.ViewModel
import com.laneboy.exchangeratesapp.presentation.screens.currencies.CurrenciesViewModel
import com.laneboy.exchangeratesapp.presentation.screens.favorites.FavoritesViewModel
import com.laneboy.exchangeratesapp.presentation.screens.filters.FiltersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CurrenciesViewModel::class)
    @Binds
    fun bindCurrenciesViewModel(viewModel: CurrenciesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    @Binds
    fun bindFiltersViewModel(viewModel: FiltersViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    @Binds
    fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}
