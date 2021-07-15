package repository

import entities.WizardDomain
import kotlinx.coroutines.flow.Flow
import responses.ResultRequired


interface WizardRepository {
    suspend fun getWizards(): ResultRequired<List<WizardDomain>>
    suspend fun getWizardsLocal(): Flow<ResultRequired<List<WizardDomain>>>
    suspend fun saveWizard(wizards: List<WizardDomain>)
    suspend fun deleteAllWizards()
}