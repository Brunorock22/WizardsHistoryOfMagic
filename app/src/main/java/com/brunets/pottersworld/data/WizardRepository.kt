package com.brunets.pottersworld.data

import com.brunets.pottersworld.data.model.Wizard
import com.google.gson.Gson
import kotlinx.coroutines.*

class WizardRepository {

    suspend fun getWizards(onSuccess: (List<Wizard>) -> Unit, onError: (String) -> Unit) {
        withContext(Dispatchers.IO) {
            var request = ApiService.api.getFamousWizards()
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    onSuccess.invoke(request.body()!!)
                } else {
                    val apiError = Gson().fromJson(
                        request.errorBody()?.charStream()?.readText(),
                        APIError::class.java
                    )
                    onError.invoke(apiError.message)
                }
            }
        }
    }
}