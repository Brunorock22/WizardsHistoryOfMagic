package com.brunets.data.remote.source

import com.brunets.data.remote.model.WizardPlayload
import com.brunets.data.utils.mapRemoteErrors
import responses.ResultRemote
import retrofit.ApiService

class RemoteDataSourceImpl: RemoteWizardDataSource {
    override suspend fun getWizardsPlaylod(): ResultRemote<List<WizardPlayload>> {
        return try {
            ResultRemote.Success(
                response = ApiService.api.getFamousWizards()
            )
        } catch (throwable: Throwable) {
           return throwable.mapRemoteErrors()
        }
    }
}