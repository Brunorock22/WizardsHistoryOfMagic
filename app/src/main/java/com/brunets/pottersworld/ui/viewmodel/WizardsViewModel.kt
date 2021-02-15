package com.brunets.pottersworld.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.brunets.pottersworld.data.ApiService
import com.brunets.pottersworld.ui.model.Wizard
import kotlinx.coroutines.*

class WizardsViewModel(application: Application) : AndroidViewModel(application) {
    var wizards = MutableLiveData<ArrayList<Wizard>>()
    var errorMessage = MutableLiveData<String>()
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage.postValue(exception.localizedMessage)
    }


    fun getWizards() {
        CoroutineScope(Dispatchers.IO + errorHandler).launch() {
            var request = ApiService.api.getFamousWizards()
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    wizards.value = request.body()
                }else{
                    errorMessage.value = request.message()
                }
            }
        }
    }


}