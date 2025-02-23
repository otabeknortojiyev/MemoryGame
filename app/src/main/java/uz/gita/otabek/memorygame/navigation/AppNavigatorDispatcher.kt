package uz.gita.otabek.memorygame.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigatorDispatcher @Inject constructor() : AppNavigator, AppNavigationHandler {
    override val navigationStack = MutableSharedFlow<AppNavigationArgs>()

    private suspend fun navigator(args: AppNavigationArgs) {
        navigationStack.emit(args)
    }

    override suspend fun navigateTo(screen: AppScreen) = navigator {
        navigate(screen)
    }

    override suspend fun back() = navigator {
        navigateUp()
    }
}