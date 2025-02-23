package uz.gita.otabek.memorygame.data.repository

import android.widget.ImageView
import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.data.local.LocalStorage
import uz.gita.otabek.memorygame.data.models.CardData
import uz.gita.otabek.memorygame.domain.repository.GameRepository
import uz.gita.otabek.memorygame.utils.LevelEnums
import uz.gita.otabek.memorygame.utils.closeCard
import uz.gita.otabek.memorygame.utils.hideCard
import uz.gita.otabek.memorygame.utils.openCard
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(private val storage: LocalStorage) : GameRepository {
    private val ls = ArrayList<CardData>()
    private var firstOpen = -1
    private var secondOpen = -1


    init {
        ls.add(CardData(1, R.drawable.image_1))
        ls.add(CardData(2, R.drawable.image_2))
        ls.add(CardData(3, R.drawable.image_3))
        ls.add(CardData(4, R.drawable.image_4))
        ls.add(CardData(5, R.drawable.image_5))
        ls.add(CardData(6, R.drawable.image_6))
        ls.add(CardData(7, R.drawable.image_7))
        ls.add(CardData(8, R.drawable.image_8))
        ls.add(CardData(9, R.drawable.image_9))
        ls.add(CardData(10, R.drawable.image_10))
        ls.add(CardData(11, R.drawable.image_11))
        ls.add(CardData(12, R.drawable.image_12))
        ls.add(CardData(13, R.drawable.image_13))
        ls.add(CardData(14, R.drawable.image_14))
        ls.add(CardData(15, R.drawable.image_15))
        ls.add(CardData(16, R.drawable.image_16))
        ls.add(CardData(17, R.drawable.image_17))
        ls.add(CardData(18, R.drawable.image_18))
        ls.add(CardData(19, R.drawable.image_19))
        ls.add(CardData(20, R.drawable.image_20))
        ls.add(CardData(21, R.drawable.image_21))
        ls.add(CardData(22, R.drawable.image_22))
        ls.add(CardData(23, R.drawable.image_23))
        ls.add(CardData(24, R.drawable.image_24))
        ls.add(CardData(25, R.drawable.image_25))
    }

    override val isWin = MutableStateFlow(false)


    override fun getCardDataByLevel(level: LevelEnums): List<CardData> {
        val count = level.horCount * level.verCount
        ls.shuffle()
        val result = ArrayList<CardData>(count)
        val l = ls.subList(0, count / 2)
        result.addAll(l)
        result.addAll(l)
        result.shuffle()
        return result
    }

    override fun setClickEventToImages(images: ArrayList<ImageView>) {
        isWin.value = false
        storage.count = images.size
        images.forEachIndexed { index, view ->
            view.setOnClickListener {
                if (firstOpen == -1 && index != firstOpen) {
                    firstOpen = index
                    view.openCard()
                } else if (secondOpen == -1 && index != secondOpen && index != firstOpen) {
                    secondOpen = index
                    view.openCard {
                        check(images)
                    }
                }
            }
        }
    }

    override fun changeIsWin() {
        isWin.value = false
    }

    private fun check(images: ArrayList<ImageView>) {
        if ((images[firstOpen].tag as CardData).id == (images[secondOpen].tag as CardData).id) {
            images[firstOpen].hideCard()
            images[secondOpen].hideCard {
                firstOpen = -1
                secondOpen = -1
            }
            storage.count -= 2
            if (storage.count == 0) {
                isWin.value = true
                when (storage.level) {
                    "easy" -> {
                        storage.easyAttempts += 1
                    }

                    "medium" -> {
                        storage.mediumAttempts += 1
                    }

                    else -> {
                        storage.hardAttempts += 1
                    }
                }
            }
        } else {
            images[firstOpen].closeCard()
            images[secondOpen].closeCard {
                firstOpen = -1
                secondOpen = -1
            }
        }
    }
}