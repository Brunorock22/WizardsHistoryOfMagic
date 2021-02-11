package com.brunets.pottersworld.data

import com.brunets.pottersworld.ui.model.Wizard
import retrofit2.Response
import retrofit2.http.GET

interface WizardApi {
    @GET("famous-wizards")
    suspend fun getFamousWizards(): Response<ArrayList<Wizard>>
}