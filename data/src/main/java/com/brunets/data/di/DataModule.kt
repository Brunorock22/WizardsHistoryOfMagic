package com.brunets.data.di

import com.brunets.data.local.mapper.WizardCacheMapper
import com.brunets.data.local.source.LocalDataSource
import com.brunets.data.local.source.LocalDataSourceImpl
import org.koin.dsl.module

val localDataSource = module {
    factory<LocalDataSource> { LocalDataSourceImpl(get(), get()) }
    factory { WizardCacheMapper() }
}