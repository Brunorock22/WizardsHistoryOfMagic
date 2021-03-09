package com.brunets.pottersworld.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.brunets.pottersworld.R
import com.brunets.pottersworld.utils.loadImage
import kotlinx.android.synthetic.main.fragment_wizard_details.*


class WizardDetailsFragment : Fragment() {
    private val args: WizardDetailsFragmentArgs by navArgs()

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
            wizard_detail_age.text = "Age: ${args.wizardAge}"
            wizard_detail_description.text = args.wizardDescription
            wizard_detail_photo.loadImage(args.wizardPhoto)
            wizard_detail_photo.setOnClickListener {
                it.blink()
            }
            wizard_detail_name.blink(2)
            wizard_detail_name.setOnClickListener {
                it.blink(3)
            }

        }
    }

    fun View.rotateUp() {
        animate().apply {
            rotationX(360f)
            duration = 1000
        }.start()
    }

    fun View.blink(
        times: Int = Animation.INFINITE,
        duration: Long = 500L,
        offset: Long = 100L,
        minAlpha: Float = 0.1f,
        maxAlpha: Float = 1.0f,
        repeatMode: Int = Animation.REVERSE
    ) {
        startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
            it.duration = duration
            it.startOffset = offset
            it.repeatMode = repeatMode
            it.repeatCount = times
        })
    }
}