package uz.gita.otabek.memorygame.presentation.screens.home

import uz.gita.otabek.memorygame.utils.LevelEnums

interface HomeScreenVm {
    fun clickLevel(levelEnums: LevelEnums)
    fun clickSettings()
    fun clickInfo()
    fun clickQuit()
}