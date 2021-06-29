package usecases

import entities.WizardDomain
import kotlinx.coroutines.flow.Flow
import repository.WizardRepository
import responses.ResultRequired

interface WizardUseCases {
    suspend fun requestWizards(): ResultWizards

    sealed class ResultWizards {
        data class Wizards(val list: List<WizardDomain>) : ResultWizards()
        object NoWizards : ResultWizards()
        data class  Error(val throwable: Throwable) : ResultWizards()
    }
}

class WizardUseCasesImpl(private val repository: WizardRepository) : WizardUseCases {

    override suspend fun requestWizards(
    ): WizardUseCases.ResultWizards {
        return when (val response = repository.getWizards()) {
            is ResultRequired.Success -> {
                when{
                    response.result.isEmpty() -> WizardUseCases.ResultWizards.NoWizards
                    else -> WizardUseCases.ResultWizards.Wizards(response.result)
                }

            }
            is ResultRequired.Error -> {
                WizardUseCases.ResultWizards.Error(response.throwable)

            }

        }
    }
}