package com.brunets.data

import com.brunets.data.local.database.WizardDao
import com.brunets.data.remote.mapper.WizardRemoteMapper
import com.brunets.data.remote.source.RemoteWizardDataSource
import entities.WizardDomain
import repository.WizardRepository
import responses.ResultRemote
import responses.ResultRequired

class WizardRepositoryImpl(
    private val remoteWizardWizardDataSource: RemoteWizardDataSource,
    private val wizardDao: WizardDao
) : WizardRepository {
    override suspend fun getWizards(

    ): ResultRequired<List<WizardDomain>> {

        return when (val dataSourceResult = remoteWizardWizardDataSource.getWizardsPlaylod()) {
            is ResultRemote.Success -> {
                val wizardMapper = WizardRemoteMapper.mapList(dataSourceResult.response)
                ResultRequired.Success(wizardMapper)
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(dataSourceResult.throwable)
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