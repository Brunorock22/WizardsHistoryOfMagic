package com.brunets.pottersworld.data

import androidx.lifecycle.MutableLiveData
import com.brunets.pottersworld.data.model.Wizard
import kotlinx.coroutines.*

class WizardRepository(var wizards: MutableLiveData<ArrayList<Wizard>>,
                       val errorMessage : MutableLiveData<String>) {
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage.postValue(exception.localizedMessage)
    }


    fun getWizards(){
        CoroutineScope(Dispatchers.IO + errorHandler).launch() {
            var request = ApiService.api.getFamousWizards()
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    wizards.value = request.body()!!
                }else{
                    errorMessage.value = request.message()
                }
            }
        }
    }
}