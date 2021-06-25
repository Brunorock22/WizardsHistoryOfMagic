import org.koin.dsl.module
import usecases.WizardUseCases
import usecases.WizardUseCasesImpl

val useCaseModule = module {
    factory<WizardUseCases> {
        WizardUseCasesImpl(
            repository = get()
        )
    }
}

