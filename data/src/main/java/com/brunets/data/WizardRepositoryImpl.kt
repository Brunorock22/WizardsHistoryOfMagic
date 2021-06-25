package com.brunets.data

import com.brunets.data.local.database.WizardDao
import com.brunets.data.remote.mapper.WizardRemoteMapper
import com.brunets.data.remote.source.RemoteWizardDataSource
import entities.WizardDomain
import repository.WizardRepository
import responses.ResultRemote

class WizardRepositoryImpl(
    private val remoteWizardWizardDataSource: RemoteWizardDataSource,
    private val wizardDao: WizardDao
) : WizardRepository {
    override suspend fun getWizards(
        onSuccess: (List<WizardDomain>) -> Unit,
        onError: (String) -> Unit
    ) {

        when (val dataSourceResult = remoteWizardWizardDataSource.getWizardsPlaylod()) {
            is ResultRemote.Success -> {
                onSuccess.invoke(WizardRemoteMapper.mapList(dataSourceResult.response))
            }
            is ResultRemote.ErrorResponse -> {
                onError.invoke(dataSourceResult.throwable.localizedMessage)
            }
        }
    }

//    private suspend fun verifyWizardsFromDB() {
//        val wizardsDataBase = wizardDao.findAll()
//        if (wizardsDataBase.isEmpty()) {
//            requestWizards()
//        } else {
//            loading.postValue(false)
//            wizards.postValue(wizardsDataBase)
//        }
//    }
}