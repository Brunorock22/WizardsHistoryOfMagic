package com.brunets.data.local.source

import entities.WizardDomain
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getWizards(): Flow<List<WizardDomain>>
    suspend fun deleteAllWizard()
    suspend fun insertWizards(wizards: List<WizardDomain>)
}