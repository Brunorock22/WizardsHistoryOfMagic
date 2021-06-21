package com.brunets.pottersworld.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.brunets.pottersworld.ui.MainViewPagerFragment
import com.brunets.pottersworld.ui.SpellsFragment
import com.brunets.pottersworld.ui.WizardsFragment
import org.koin.java.KoinJavaComponent.inject

class ViewPagerAdapter(fa: MainViewPagerFragment, private val wizardsFragment: WizardsFragment, private val spellsFragment: SpellsFragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment{
        return if (position == 0)
            wizardsFragment
        else
            spellsFragment
    }


}