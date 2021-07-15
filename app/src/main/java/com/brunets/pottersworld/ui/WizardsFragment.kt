package com.brunets.pottersworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brunets.pottersworld.R
import com.brunets.pottersworld.ui.adapter.WizardsAdapter
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import com.brunets.pottersworld.utils.visible
import com.example.fakelibrary.FakeToaster
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_wizards.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WizardsFragment : Fragment() {
    val viewModel by viewModel<WizardsViewModel>()
    lateinit var adapterWizards: WizardsAdapter
  
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_wizards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.wizards.observe(viewLifecycleOwner, Observer { wizards ->
            recycler_wizards.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapterWizards = WizardsAdapter(wizards)
            recycler_wizards.adapter = adapterWizards

            adapterWizards.onItemClick = {
                Snackbar.make(requireView(), it.name, Snackbar.LENGTH_SHORT).show()

                val action =
                    MainViewPagerFragmentDirections.actionWizardsFragmentToWizardDetailsFragment(
                        it.name,
                        it.age ?: 0,
                        it.photo,
                        it.description ?: ""
                    )
                Navigation.findNavController(view).navigate(action)
            }

            errorContainer.visible(false)
            recycler_wizards.visible(true)

            val fake = FakeToaster
            fake.showJoke(requireContext())

            swipeWizards.isRefreshing = false
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            wizardsProgress.visible(false)
            recycler_wizards.visible(false)
            errorContainer.visible(true)
            errorWizards.text = it
            swipeWizards.isRefreshing = false

        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                wizardsProgress.visibility = View.VISIBLE
                errorContainer.visibility = View.GONE
                recycler_wizards.visibility = View.GONE
            } else {

                wizardsProgress.visibility = View.GONE

            }
        })

        viewModel.getLocalWizards()
        viewModel.getRemoteWizards()

        swipeWizards.setOnRefreshListener {
            viewModel.getRemoteWizards()
        }

    }
}