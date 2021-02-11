package com.brunets.pottersworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brunets.pottersworld.R
import com.brunets.pottersworld.ui.adapter.WizardsAdapter
import com.brunets.pottersworld.ui.model.Wizard
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import kotlinx.android.synthetic.main.fragment_wizards.*


class WizardsFragment : Fragment() {
    lateinit var wizarViewModel: WizardsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_wizards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wizarViewModel = ViewModelProviders.of(this).get(WizardsViewModel::class.java)

        wizarViewModel.wizards.observe(viewLifecycleOwner, Observer { wizards ->
            recycler_wizards.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recycler_wizards.adapter = WizardsAdapter(wizards)

        })

        wizarViewModel.getWizards()


    }
}