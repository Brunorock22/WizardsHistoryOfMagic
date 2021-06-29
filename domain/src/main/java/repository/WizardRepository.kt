package repository

import entities.WizardDomain
import responses.ResultRequired


interface WizardRepository {
    suspend fun getWizards(): ResultRequired<List<WizardDomain>>
}