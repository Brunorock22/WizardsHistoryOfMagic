package com.brunets.pottersworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brunets.pottersworld.R
import com.brunets.pottersworld.ui.adapter.WizardsAdapter
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_wizards.*


class WizardsFragment : Fragment() {
    lateinit var wizarViewModel: WizardsViewModel
    lateinit var adapterWizards: WizardsAdapter
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
            adapterWizards = WizardsAdapter(wizards)
            recycler_wizards.adapter = adapterWizards

            adapterWizards.onItemClick = {
                Snackbar.make(requireView(), it.name, Snackbar.LENGTH_SHORT).show()

                val action = MainViewPagerFragmentDirections.actionWizardsFragmentToWizardDetailsFragment(
                    it.name,
                    it.age,
                    it.photo,
                    it.description ?: ""
                )
                Navigation.findNavController(view).navigate(action)
            }

            wizardsProgress.visibility = View.GONE
            recycler_wizards.visibility = View.VISIBLE

        })

        wizarViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            wizardsProgress.visibility = View.GONE
            errorContainer.visibility = View.VISIBLE
            errorWizards.text = it
        })

        wizarViewModel.getWizards()

    }
}