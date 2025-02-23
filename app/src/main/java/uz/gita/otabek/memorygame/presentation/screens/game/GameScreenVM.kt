package uz.gita.otabek.memorygame.presentation.screens.game

import android.widget.ImageView
import kotlinx.coroutines.flow.StateFlow
import uz.gita.otabek.memorygame.data.models.CardData
import uz.gita.otabek.memorygame.utils.LevelEnums

interface GameScreenVM {
    val cardsFlow: StateFlow<List<CardData>>
    val isWin: StateFlow<Boolean>
    fun loadCardByLevel(level: LevelEnums)
    fun setClickEventToImages(images: ArrayList<ImageView>)
    fun clickOk()
    fun changeIsWin()
}