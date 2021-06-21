package repository

import entities.WizardData


interface WizardRepository {
    suspend fun getWizards(onSuccess: (List<WizardData>) -> Unit, onError: (String) -> Unit)
}