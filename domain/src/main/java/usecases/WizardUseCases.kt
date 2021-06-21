package usecases

import entities.WizardData
import repository.WizardRepository


class WizardUseCases(private val repository: WizardRepository) {

    suspend fun requestWizards(
        onSuccess: (List<WizardData>) -> Unit,
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