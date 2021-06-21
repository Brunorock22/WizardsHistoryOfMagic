package com.brunets.pottersworld.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import repository.WizardRepositoryImpl
import com.brunets.pottersworld.data.model.Wizard
import com.brunets.pottersworld.data.model.WizardDao
import entities.WizardData
import kotlinx.coroutines.*
import repository.WizardRepository
import usecases.WizardUseCases

class WizardsViewModel(private val useCases: WizardUseCases, private val dao: WizardDao) :
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
        useCases.requestWizards(
            onSuccess = { wizardsData ->
                val nameMap: List<Wizard> = wizardsData.map { Wizard(it.name,it.photo, it.age, it.description) }

                wizardsData.map {
                    Wizard.convetData(it) }
                wizards.value = nameMap
                loading.value = false
                saveWizards(nameMap)
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