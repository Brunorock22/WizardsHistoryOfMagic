package com.brunets.pottersworld

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

import com.brunets.pottersworld.ui.adapter.ScreenSlidePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        pager.adapter = pagerAdapter

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            if (position == 0){
                tab.text = "Wizards"
            }else{
                tab.text = "Spells"
            }
        }.attach()
    }
}