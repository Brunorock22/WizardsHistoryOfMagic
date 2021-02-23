package com.brunets.pottersworld.data

import kotlinx.coroutines.*

class WizardRepository {

    suspend fun getWizards(): Any {
        return withContext(Dispatchers.IO) {
            var request = ApiService.api.getFamousWizards()
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    return@withContext request.body()!!
                } else {
                    return@withContext request.message()
                }
            }
        }
    }
}