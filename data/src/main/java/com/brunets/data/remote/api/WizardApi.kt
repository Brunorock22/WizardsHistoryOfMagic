package com.brunets.data.remote.api

import com.brunets.data.remote.model.WizardPlayload
import retrofit2.http.GET

interface WizardApi {
    @GET("famous-wizards")
    suspend fun getFamousWizards(): List<WizardPlayload>
}