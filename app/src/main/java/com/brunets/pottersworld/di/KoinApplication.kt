package com.brunets.pottersworld.di

import android.app.Application
import com.brunets.pottersworld.di.databaseModule
import com.brunets.pottersworld.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(listOf(mainModule, databaseModule))

        }
    }
}