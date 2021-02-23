package com.brunets.pottersworld.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.brunets.pottersworld.data.ApiService
import com.brunets.pottersworld.data.WizardRepository
import com.brunets.pottersworld.data.model.Wizard
import kotlinx.coroutines.*

class WizardsViewModel(application: Application) : AndroidViewModel(application) {
    var wizards = MutableLiveData<ArrayList<Wizard>>()
    var errorMessage = MutableLiveData<String>()
    val wizardRepository =  WizardRepository(wizards, errorMessage)


    fun  getWizards() {
        wizardRepository.getWizards()
    }


}