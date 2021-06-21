import org.koin.dsl.module
import repository.WizardRepository
import repository.WizardRepositoryImpl
import usecases.WizardUseCases

val useCaseModule = module {
    single {
        WizardUseCases(
            repository = get()
        )
    }
}

val repositoryModule = module {
    single<WizardRepository> { WizardRepositoryImpl() }

}