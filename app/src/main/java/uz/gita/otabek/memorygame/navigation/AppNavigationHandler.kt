package uz.gita.otabek.memorygame.navigation

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.memorygame.navigation.AppNavigationArgs

interface AppNavigationHandler {
    val navigationStack: Flow<AppNavigationArgs>
}