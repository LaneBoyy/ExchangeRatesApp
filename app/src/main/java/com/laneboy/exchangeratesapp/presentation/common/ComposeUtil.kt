package com.laneboy.exchangeratesapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.laneboy.exchangeratesapp.App
import com.laneboy.exchangeratesapp.di.ApplicationComponent

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as App).component
}