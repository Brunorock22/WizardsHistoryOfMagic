package com.brunets.pottersworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.brunets.pottersworld.R
import kotlinx.android.synthetic.main.fragment_wizard_details.*

class WizardDetailsFragment : Fragment() {
    val args: WizardDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wizard_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            wizard_detail_name.text = args.wizardName
            wizard_detail_age.text = args.wizardAge.toString()
            wizard_detail_photo.loadImage(args.wizardPhoto)
        }
    }
}