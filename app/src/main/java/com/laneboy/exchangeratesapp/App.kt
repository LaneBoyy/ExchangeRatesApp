package com.laneboy.exchangeratesapp

import android.app.Application
import com.laneboy.exchangeratesapp.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}