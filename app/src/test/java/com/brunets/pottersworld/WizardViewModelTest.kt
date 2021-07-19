package com.brunets.pottersworld

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import com.nhaarman.mockitokotlin2.verify
import entities.WizardDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import responses.ResultRequired
import usecases.WizardUseCases

@RunWith(MockitoJUnitRunner::class)
class WizardViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var wizardObserver: Observer<List<WizardDomain>>

    @Mock
    private lateinit var errorObserver: Observer<String>

    private lateinit var viewModel: WizardsViewModel

    private val wizards = listOf(WizardDomain("Harry Potter", "", 0, ""))
    private val successUseCase = MockSuccessWizardUseCase(wizards)
    private val errorUseCase = MockFailedWizardUseCase()

    @Test
    fun `when view model getWizards get success then sets wizards liveData`() {

        runBlocking {
            viewModel = WizardsViewModel(successUseCase)
            viewModel.wizards.observeForever(wizardObserver)
            viewModel.feedFields(successUseCase.requestWizards())
            verify(wizardObserver).onChanged(wizards)
        }


    }

    @Test
    fun `when local database return object feed scree`() {
        runBlocking {
            viewModel = WizardsViewModel(successUseCase)
            viewModel.wizards.observeForever(wizardObserver)
            successUseCase.fetchWizards().collect { cache ->
                when (cache) {
                    is ResultRequired.Success -> {
                        viewModel.feedFields(WizardUseCases.ResultWizards.Wizards(cache.result))
                        verify(wizardObserver).onChanged(cache.result)
                    }
                    else -> null
                }
            }
        }
    }

    @Test
    fun `when viewmodel get wizards error in request set error livedata`() {
        runBlocking {
            viewModel = WizardsViewModel(errorUseCase)
            viewModel.errorMessage.observeForever(errorObserver)
            viewModel.feedFields(errorUseCase.requestWizards())
            verify(errorObserver).onChanged("FAKE ERROR")
        }
    }

    @Test
    fun `when get error in database`() {
        runBlocking {
            viewModel = WizardsViewModel(errorUseCase)
            errorUseCase.fetchWizards().collect { cached ->
                when (cached) {
                    is ResultRequired.Error -> {
                        viewModel.errorMessage.observeForever(errorObserver)
                        viewModel.feedFields(WizardUseCases.ResultWizards.Error(cached.throwable))
                        verify(errorObserver).onChanged("FAKE DATABASE ERROR")
                    }
                }
            }
        }
    }
}

class MockSuccessWizardUseCase(private val list: List<WizardDomain>) : WizardUseCases {
    private val wizards = listOf(WizardDomain("Harry Potter", "", 25, ""))

    override suspend fun requestWizards(): WizardUseCases.ResultWizards {
        return WizardUseCases.ResultWizards.Wizards(list)
    }

    override suspend fun fetchWizards(): Flow<ResultRequired<List<WizardDomain>>> {
        return flow {
            val result = ResultRequired.Success(wizards)
            emit(result)
        }
    }
}


class MockFailedWizardUseCase() : WizardUseCases {
    override suspend fun requestWizards(): WizardUseCases.ResultWizards {
        return WizardUseCases.ResultWizards.Error(Throwable(message = "FAKE ERROR"))
    }

    override suspend fun fetchWizards(): Flow<ResultRequired<List<WizardDomain>>> {
        return flow {
            val result = ResultRequired.Error(Throwable(message = "FAKE DATABASE ERROR"))
            emit(result)
        }
    }

}