package com.brunets.pottersworld.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunets.pottersworld.data.WizardRepository
import com.brunets.pottersworld.data.model.Wizard
import com.brunets.pottersworld.data.model.WizardDao
import kotlinx.coroutines.*

class WizardsViewModel(private val wizardRepository: WizardRepository, private val dao: WizardDao) :
    ViewModel() {
    val wizards = MutableLiveData<List<Wizard>>()
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
            verifyWizardsFromDB()
        }
    }

    private suspend fun requestWizards() {
        loading.postValue(true)
        wizardRepository.getWizards(
            onSuccess = {
                wizards.value = it
                loading.value = false
                saveWizards(it)
            }, onError = {
                loading.value = false
                errorMessage.value = it
            }
        )
    }


    private fun saveWizards(wizards: List<Wizard>) {
        CoroutineScope(Dispatchers.IO + errorHandler).launch {
            dao.deleteAll()
            dao.insertAll(wizard = wizards)
        }
    }

    private suspend fun verifyWizardsFromDB() {
            val wizardsDataBase = dao.findAll()
            if (wizardsDataBase.isEmpty()) {
                requestWizards()
            } else {
                loading.postValue(false)
                wizards.postValue(wizardsDataBase)
            }


    }
}