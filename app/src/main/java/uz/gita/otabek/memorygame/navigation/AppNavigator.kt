package uz.gita.otabek.memorygame.navigation

import uz.gita.otabek.memorygame.navigation.AppScreen
import uz.gita.otabek.memorygame.utils.LevelEnums

interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}