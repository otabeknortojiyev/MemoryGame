package uz.gita.otabek.memorygame.presentation.screens.game

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gita.otabek.memorygame.data.models.CardData
import uz.gita.otabek.memorygame.domain.repository.GameRepository
import uz.gita.otabek.memorygame.navigation.AppNavigator
import uz.gita.otabek.memorygame.utils.LevelEnums
import javax.inject.Inject

@HiltViewModel
class GameScreenVMImpl @Inject constructor(
    private val gameRepository: GameRepository, private val appNavigator: AppNavigator
) : GameScreenVM, ViewModel() {
    override val cardsFlow = MutableStateFlow<List<CardData>>(emptyList())
    override val isWin: StateFlow<Boolean> = gameRepository.isWin

    override fun loadCardByLevel(level: LevelEnums) {
        viewModelScope.launch {
            cardsFlow.emit(gameRepository.getCardDataByLevel(level))
        }
    }

    override fun setClickEventToImages(images: ArrayList<ImageView>) {
        gameRepository.setClickEventToImages(images)
    }

    override fun clickOk() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }

    override fun changeIsWin() {
        gameRepository.changeIsWin()
    }
}