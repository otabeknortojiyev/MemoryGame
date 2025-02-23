package uz.gita.otabek.memorygame.presentation.screens.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.data.local.LocalStorage
import uz.gita.otabek.memorygame.data.models.CardData
import uz.gita.otabek.memorygame.databinding.DialogGameOverBinding
import uz.gita.otabek.memorygame.databinding.ScreenGameBinding
import uz.gita.otabek.memorygame.utils.LevelEnums
import uz.gita.otabek.memorygame.utils.createDialog

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val navArgs by navArgs<GameScreenArgs>()
    private val vm: GameScreenVM by viewModels<GameScreenVMImpl>()
    private lateinit var level: LevelEnums
    private var _h = 0
    private var _w = 0
    private val images = ArrayList<ImageView>()
    private lateinit var storage: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setObserves()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        storage = LocalStorage(requireContext())
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
        when (navArgs.level) {
            LevelEnums.EASY -> {
                storage.level = "easy"
            }

            LevelEnums.MEDIUM -> {
                storage.level = "medium"
            }

            else -> {
                storage.level = "hard"
            }
        }

        binding.attempt.text = when (navArgs.level) {
            LevelEnums.EASY -> {
                storage.easyAttempts.toString()
            }

            LevelEnums.MEDIUM -> {
                storage.mediumAttempts.toString()
            }

            else -> {
                storage.hardAttempts.toString()
            }
        }
        level = navArgs.level
        binding.container.post {
            _h = binding.container.height / level.horCount
            _w = binding.container.width / level.verCount
            vm.loadCardByLevel(level)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObserves() {
        vm.cardsFlow.onEach {
            if (it.isNotEmpty()) {
                cardsObserver(it)
            }
        }.launchIn(lifecycleScope)
        vm.isWin.onEach {
            if (it) {
                val binding = DialogGameOverBinding.inflate(LayoutInflater.from(requireContext()))
                val dialog = createDialog(binding = binding, R.style.TransparentDialog, true)
                dialog.setCancelable(false)
                binding.tvOkay.setOnClickListener {
                    dialog.dismiss()
                    vm.changeIsWin()
                    vm.clickOk()
                }
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }

    private fun cardsObserver(list: List<CardData>) {
        for (i in 0 until level.verCount) {
            for (j in 0 until level.horCount) {
                val imageView = ImageView(requireContext())
                binding.container.addView(imageView)
                val lp = imageView.layoutParams as ConstraintLayout.LayoutParams
                lp.apply {
                    width = _w
                    height = _h
                }
                imageView.x = i * _w * 1f
                imageView.y = j * _h * 1f
                imageView.layoutParams = lp
                imageView.tag = list[i * level.horCount + j]
                imageView.setImageResource(R.drawable.green_button)
                images.add(imageView)
            }
        }
        vm.setClickEventToImages(images)
    }
}