package com.brunets.data.remote.api

import com.brunets.data.remote.model.WizardPlayload
import entities.WizardDomain
import retrofit2.Response
import retrofit2.http.GET

interface WizardApi {
    @GET("famous-wizards")
    suspend fun getFamousWizards(): List<WizardPlayload>
}