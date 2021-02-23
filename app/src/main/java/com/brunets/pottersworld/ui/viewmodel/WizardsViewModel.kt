package com.brunets.pottersworld.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunets.pottersworld.data.WizardRepository
import com.brunets.pottersworld.data.model.Wizard
import kotlinx.coroutines.*

class WizardsViewModel(private val wizardRepository: WizardRepository) : ViewModel() {
    val wizards = MutableLiveData<ArrayList<Wizard>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        CoroutineScope(Dispatchers.Main).launch{
            loading.value = false
            errorMessage.value = exception.localizedMessage
        }
    }

    fun getWizards() {
        loading.value = true
        CoroutineScope(Dispatchers.Main+ errorHandler).launch {
            var response = async {
                wizardRepository.getWizards()
            }.await()
            if (response is ArrayList<*>) {
                loading.value = false
                wizards.value = response as ArrayList<Wizard>
            } else {
                loading.value = false
                errorMessage.value = response as String
            }
        }

    }


}