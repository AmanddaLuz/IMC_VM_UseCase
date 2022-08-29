package com.amandaluz

import android.app.Application
import com.amandaluz.imccalculator.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CustomApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        startKoin{
            androidLogger()
            androidContext(this@CustomApp)
        }
    }
}