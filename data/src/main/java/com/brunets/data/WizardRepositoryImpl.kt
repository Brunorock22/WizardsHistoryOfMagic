package com.brunets.data

import com.brunets.data.local.source.LocalDataSource
import com.brunets.data.remote.mapper.WizardRemoteMapper
import com.brunets.data.remote.source.RemoteWizardDataSource
import entities.WizardDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repository.WizardRepository
import responses.ResultRemote
import responses.ResultRequired

class WizardRepositoryImpl(
    private val remoteWizardWizardDataSource: RemoteWizardDataSource,
    private val localDataSource: LocalDataSource
) : WizardRepository {
    override suspend fun getWizards(): ResultRequired<List<WizardDomain>> {
        return when (val dataSourceResult = remoteWizardWizardDataSource.getWizardsPlaylod()) {
            is ResultRemote.Success -> {
                val wizardMapper = WizardRemoteMapper.mapList(dataSourceResult.response)
                deleteAllWizards()
                saveWizard(wizardMapper)
                ResultRequired.Success(wizardMapper)
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(dataSourceResult.throwable)
            }
        }
    }

    override suspend fun getWizardsLocal(): Flow<ResultRequired<List<WizardDomain>>> {
        return localDataSource.getWizards().map { cache ->
            val result = ResultRequired.Success(cache)


            result
        }
    }

    override suspend fun saveWizard(wizards: List<WizardDomain>) {
        localDataSource.insertWizards(wizards)
    }

    override suspend fun deleteAllWizards() {
        localDataSource.deleteAllWizard()
    }

}