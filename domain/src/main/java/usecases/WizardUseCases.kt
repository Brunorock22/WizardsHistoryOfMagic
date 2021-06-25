package usecases

import entities.WizardDomain
import repository.WizardRepository

interface WizardUseCases{
    suspend fun requestWizards(
        onSuccess: (List<WizardDomain>) -> Unit,
        onError: (String?) -> Unit
    )
}

class WizardUseCasesImpl(private val repository: WizardRepository): WizardUseCases {

    override suspend fun requestWizards(
        onSuccess: (List<WizardDomain>) -> Unit,
        onError: (String?) -> Unit
    ) {
        repository.getWizards(
            onSuccess = {
                onSuccess.invoke(it)
            }, onError = {
                onError.invoke(it)

            }
        )
    }
}