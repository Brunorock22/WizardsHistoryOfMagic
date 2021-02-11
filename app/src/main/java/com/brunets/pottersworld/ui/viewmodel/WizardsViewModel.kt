package com.brunets.pottersworld.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.brunets.pottersworld.data.ApiService
import com.brunets.pottersworld.ui.model.Wizard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WizardsViewModel(application: Application) : AndroidViewModel(application) {
    var wizards = MutableLiveData<ArrayList<Wizard>>()

    fun getWizards() {
        CoroutineScope(Dispatchers.IO).launch {

            var request = ApiService.api.getFamousWizards()
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    wizards.value = request.body()
                }
            }
        }
    }
}