package com.brunets.pottersworld.di

import com.brunets.data.WizardRepositoryImpl
import org.koin.dsl.module
import repository.WizardRepository

val repositoryModule = module {
    single<WizardRepository> { WizardRepositoryImpl(get(), get()) }

}