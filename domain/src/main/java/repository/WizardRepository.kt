package repository

import entities.WizardDomain


interface WizardRepository {
    suspend fun getWizards(onSuccess: (List<WizardDomain>) -> Unit, onError: (String) -> Unit)
}