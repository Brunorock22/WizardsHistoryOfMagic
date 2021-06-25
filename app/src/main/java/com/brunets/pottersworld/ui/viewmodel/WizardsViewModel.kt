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
            useCases.requestWizards(
                onSuccess = { wizardsData ->
                    wizards.postValue(wizardsData)
                    loading.postValue(false)
                }, onError = {
                    loading.postValue(false)
                    errorMessage.postValue(it)
                }
            )
        }
    }

}