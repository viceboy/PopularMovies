package com.viceboy.popularmovies

import android.app.Application
import com.viceboy.popularmovies.di.databaseModule
import com.viceboy.popularmovies.di.networkModule
import com.viceboy.popularmovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val modules = listOf(networkModule, databaseModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }
}