package uz.gita.otabek.memorygame.domain.repository

import android.widget.ImageView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.otabek.memorygame.data.models.CardData
import uz.gita.otabek.memorygame.utils.LevelEnums

interface GameRepository {
    val isWin: StateFlow<Boolean>
    fun getCardDataByLevel(level: LevelEnums): List<CardData>
    fun setClickEventToImages(images: ArrayList<ImageView>)
    fun changeIsWin()
}