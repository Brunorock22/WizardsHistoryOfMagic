package com.brunets.pottersworld

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunets.pottersworld.ui.viewmodel.WizardsViewModel
import com.nhaarman.mockitokotlin2.verify
import entities.WizardDomain
import kotlinx.coroutines.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import usecases.WizardUseCases

@RunWith(MockitoJUnitRunner::class)
class WizardViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var wizardObserver: Observer<List<WizardDomain>>

    private lateinit var viewModel: WizardsViewModel


    @Test
    fun `when view model getWizards get success then sets wizards liveData`() {
        val wizardsList = listOf(WizardDomain("Harry Potter", "", 0, ""))
        val mockUseCase = MockWizardUseCase(wizardsList)

        viewModel = WizardsViewModel(mockUseCase)
        viewModel.wizards.observeForever(wizardObserver)
        runBlocking {
            viewModel.feedFields(mockUseCase.requestWizards())
            verify(wizardObserver).onChanged(wizardsList)
        }
    }
}

class MockWizardUseCase(private val list: List<WizardDomain>) : WizardUseCases {
    override suspend fun requestWizards(): WizardUseCases.ResultWizards {
        return WizardUseCases.ResultWizards.Wizards(list)
    }

}