package repository

import com.google.gson.Gson
import entities.WizardData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit.APIError
import retrofit.ApiService

class WizardRepositoryImpl : WizardRepository {
    override suspend fun getWizards(onSuccess: (List<WizardData>) -> Unit, onError: (String) -> Unit) {
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