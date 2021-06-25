package com.brunets.pottersworld.di

import com.brunets.pottersworld.ui.MainViewPagerFragment
import com.brunets.pottersworld.ui.SpellsFragment
import com.brunets.pottersworld.ui.WizardsFragment
import com.brunets.pottersworld.ui.adapter.ViewPagerAdapter
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        WizardsViewModel(get())
    }
    single { MainViewPagerFragment() }
    single { WizardsFragment() }
    single { SpellsFragment() }

    factory {
        ViewPagerAdapter(get(), get(), get())
    }


}

