package com.brunets.pottersworld.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.brunets.pottersworld.R

import com.brunets.pottersworld.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.view_pager_main_fragment.*
import org.koin.android.ext.android.inject

class MainViewPagerFragment : Fragment() {
    private val wizardsFragment: WizardsFragment by inject()
    private val spellsFragment: SpellsFragment by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.view_pager_main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = ViewPagerAdapter(this, wizardsFragment, spellsFragment)
        pager.adapter = pagerAdapter

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            if (position == 0) {
                tab.text = "Wizards"
            } else {
                tab.text = "Spells"
            }
        }.attach()
    }
}