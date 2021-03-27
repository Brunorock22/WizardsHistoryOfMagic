package com.brunets.pottersworld.di

import android.app.Application
import androidx.room.Room

import com.brunets.pottersworld.data.WizardRepository
import com.brunets.pottersworld.data.model.DataBase
import com.brunets.pottersworld.data.model.WizardDao
import com.brunets.pottersworld.ui.MainViewPagerFragment
import com.brunets.pottersworld.ui.SpellsFragment
import com.brunets.pottersworld.ui.WizardsFragment
import com.brunets.pottersworld.ui.adapter.MainViewPagerAdapter
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        WizardsViewModel(get(), get())
    }
    single {
        WizardRepository()
    }
    single { MainViewPagerFragment() }
    single { WizardsFragment() }
    single { SpellsFragment() }

    factory {
        MainViewPagerAdapter(get(),get(),get())
    }


}
val databaseModule = module {

    fun provideDatabase(application: Application): DataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            DataBase::class.java, "database-wizard"
        ).fallbackToDestructiveMigrationFrom(1,2).build()
    }

    fun provideCountriesDao(database: DataBase): WizardDao {
        return  database.dao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}
