package retrofit

import entities.WizardData
import retrofit2.Response
import retrofit2.http.GET

interface WizardApi {
    @GET("famous-wizards")
    suspend fun getFamousWizards(): Response<ArrayList<WizardData>>
}