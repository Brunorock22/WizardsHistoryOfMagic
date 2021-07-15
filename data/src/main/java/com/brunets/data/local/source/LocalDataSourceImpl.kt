package com.brunets.data.local.source

import com.brunets.data.local.database.WizardDao
import com.brunets.data.local.mapper.WizardCacheMapper
import entities.WizardDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val wizardDao: WizardDao,
    private val wizardCacheMapper: WizardCacheMapper
) : LocalDataSource {
    override suspend fun getWizards(): Flow<List<WizardDomain>> {
        val fetchWizards = wizardDao.findAll()
        return fetchWizards.map {
            wizardCacheMapper.listCacheToDomain(it)
        }
    }

    override suspend fun deleteAllWizard() {
        wizardDao.deleteAll()
    }

    override suspend fun insertWizards(wizards: List<WizardDomain>) {
        wizardDao.insertAll(wizardCacheMapper.listDomainToCache(wizards))
    }

}