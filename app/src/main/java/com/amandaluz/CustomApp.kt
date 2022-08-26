package com.amandaluz

import android.app.Application
import com.amandaluz.imccalculator.BuildConfig
import timber.log.Timber

class CustomApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}