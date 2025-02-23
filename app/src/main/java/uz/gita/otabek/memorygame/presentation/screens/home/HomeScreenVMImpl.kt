package uz.gita.otabek.memorygame.presentation.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.gita.otabek.memorygame.navigation.AppNavigator
import uz.gita.otabek.memorygame.utils.LevelEnums
import javax.inject.Inject

@HiltViewModel
class HomeScreenVMImpl @Inject constructor(
    @ApplicationContext private val context: Context, private val appNavigator: AppNavigator
) : HomeScreenVm, ViewModel() {

    override fun clickLevel(levelEnums: LevelEnums) {
        viewModelScope.launch {
            appNavigator.navigateTo(HomeScreenDirections.actionHomeScreenToGameScreen(levelEnums))
        }
    }

    override fun clickSettings() {
        viewModelScope.launch {
            appNavigator.navigateTo(HomeScreenDirections.actionHomeScreenToSettingsScreen())
        }
    }

    override fun clickInfo() {
        viewModelScope.launch {
            appNavigator.navigateTo(HomeScreenDirections.actionHomeScreenToInfoScreen())
        }
    }

    override fun clickQuit() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
}

