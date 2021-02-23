package com.brunets.pottersworld.di

import com.brunets.pottersworld.data.WizardRepository
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        WizardsViewModel(get())
    }
    single {
        WizardRepository()
    }


}

