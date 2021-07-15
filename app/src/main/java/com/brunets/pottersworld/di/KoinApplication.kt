package com.brunets.pottersworld.di

import android.app.Application
import com.brunets.data.di.databaseModule
import com.brunets.data.di.localDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import useCaseModule

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@KoinApplication)
            modules(
                listOf(
                    mainModule,
                    databaseModule,
                    useCaseModule,
                    repositoryModule,
                    remoteDataSourceModule,
                    localDataSource
                )
            )

        }
    }
}