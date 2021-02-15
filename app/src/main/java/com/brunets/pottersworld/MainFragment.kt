package com.brunets.pottersworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import com.brunets.pottersworld.ui.adapter.ScreenSlidePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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