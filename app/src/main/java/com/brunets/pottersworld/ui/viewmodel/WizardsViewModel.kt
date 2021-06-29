package com.brunets.pottersworld.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import entities.WizardDomain
import kotlinx.coroutines.*
import usecases.WizardUseCases

class WizardsViewModel(
    private val useCases: WizardUseCases
) :
    ViewModel() {
    val wizards = MutableLiveData<List<WizardDomain>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        CoroutineScope(Dispatchers.Main).launch {
            loading.postValue(false)
            errorMessage.value = exception.localizedMessage
        }
    }

    fun getWizards() {
        CoroutineScope(Dispatchers.IO + errorHandler).launch {
            loading.postValue(true)
            feedFields(useCases.requestWizards())
        }
    }

    fun feedFields(result: WizardUseCases.ResultWizards) {
        when (result) {
            is WizardUseCases.ResultWizards.Wizards -> {
                wizards.value = (result.list)
                loading.postValue(false)
            }
            is WizardUseCases.ResultWizards.Error -> {
                loading.postValue(false)
                errorMessage.postValue(result.throwable.localizedMessage)
            }
            else -> return
        }
    }


}