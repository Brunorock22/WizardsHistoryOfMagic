package com.brunets.pottersworld.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entities.WizardDomain
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import responses.ResultRequired
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

    fun getLocalWizards() {
        viewModelScope.launch((Dispatchers.IO + errorHandler)) {
            loading.postValue(true)
            useCases.fetchWizards().collect { cached ->
                when (cached) {
                    is ResultRequired.Success -> feedFields(
                        WizardUseCases.ResultWizards.Wizards(
                            cached.result
                        )
                    )
                    is ResultRequired.Error -> feedFields(WizardUseCases.ResultWizards.Error(cached.throwable))
                }
            }
        }
    }

    fun getRemoteWizards() {
        viewModelScope.launch((Dispatchers.IO + errorHandler)) {
            useCases.requestWizards()
        }
    }

    fun feedFields(result: WizardUseCases.ResultWizards) {
        when (result) {
            is WizardUseCases.ResultWizards.Wizards -> {
                wizards.postValue(result.list)
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