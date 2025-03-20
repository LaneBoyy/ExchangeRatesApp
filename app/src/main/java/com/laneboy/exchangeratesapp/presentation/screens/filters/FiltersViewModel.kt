package com.laneboy.exchangeratesapp.presentation.screens.filters

import androidx.lifecycle.ViewModel
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FiltersViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<FiltersViewState> =
        MutableStateFlow(FiltersViewState())
    val state = _state.asStateFlow()

    fun setCurrenciesSortType(sortType: CurrenciesSortType) {
        _state.value = _state.value.copy(sortType = sortType)
    }
}