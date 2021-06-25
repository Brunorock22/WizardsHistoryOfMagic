package com.brunets.data.di

import android.app.Application
import androidx.room.Room
import com.brunets.data.local.database.DataBase
import com.brunets.data.local.database.WizardDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): DataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            DataBase::class.java, "database-wizard"
        ).build()
    }

    fun providWizardDao(database: DataBase): WizardDao {
        return database.dao
    }

    single { provideDatabase(androidApplication()) }
    single { providWizardDao(get()) }
}