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
        CoroutineScope(Dispatchers.IO+ errorHandler).launch {
            getWizardsDataFromCache()
        }
    }

    suspend fun requestWizards() {
        loading.postValue( true)
        var response = withContext(Dispatchers.IO) {
            wizardRepository.getWizards()
        }
        withContext(Dispatchers.Main){

        if (response is ArrayList<*>) {
            loading.value = false
            wizards.value = response as ArrayList<Wizard>
            saveWizards(wizards.value!!)
        } else {
            loading.value = false
            errorMessage.value = response as String
        }
        }

    }

    private suspend fun saveWizards(wizards: List<Wizard>) {
        dao.deleteAll()
        dao.insertAll(wizard = wizards)
    }

    private suspend fun getWizardsDataFromCache() {
        return withContext(Dispatchers.IO) {
            val wizardsDataBase = dao.findAll()
            if (wizardsDataBase.isEmpty()) {
                requestWizards()
            } else {
                loading.postValue(false)
                wizards.postValue(wizardsDataBase)
            }
        }

    }
}