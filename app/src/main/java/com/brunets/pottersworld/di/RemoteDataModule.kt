package com.brunets.pottersworld.di

import com.brunets.data.remote.source.RemoteDataSourceImpl
import com.brunets.data.remote.source.RemoteWizardDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory<RemoteWizardDataSource> { RemoteDataSourceImpl() }
}
