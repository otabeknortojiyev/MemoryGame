package uz.gita.otabek.memorygame.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.view.longClicks
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.application.MyApp
import uz.gita.otabek.memorygame.data.local.LocalStorage
import uz.gita.otabek.memorygame.databinding.DialogInfoBinding
import uz.gita.otabek.memorygame.databinding.ScreenHomeBinding
import uz.gita.otabek.memorygame.utils.LanguageEnums
import uz.gita.otabek.memorygame.utils.LevelEnums
import uz.gita.otabek.memorygame.utils.createDialog
import uz.gita.otabek.memorygame.utils.setLocale

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val vm: HomeScreenVm by viewModels<HomeScreenVMImpl>()
    private var settingsOpened: Boolean = false
    private lateinit var storage: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setActions()
    }

    private fun init() {
        storage = LocalStorage(requireContext())
        if (storage.isPlaying) {
            MyApp.media.start()
            storage.isPlaying = true
            MyApp.media.isLooping = true
        } else {
            MyApp.media.start()
            MyApp.media.pause()
            storage.isPlaying = false
        }
    }

    @OptIn(FlowPreview::class)
    private fun setActions() {
        binding.ivEasy.clicks().debounce(200).onEach { vm.clickLevel(LevelEnums.EASY) }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
        binding.ivMedium.clicks().debounce(200).onEach { vm.clickLevel(LevelEnums.MEDIUM) }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
        binding.ivHard.clicks().debounce(200).onEach { vm.clickLevel(LevelEnums.HARD) }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
        binding.ivSettings.clicks().debounce(0).onEach {
            if (settingsOpened) {
                binding.ll1.visibility = View.INVISIBLE
                settingsOpened = false
            } else {
                binding.ll1.visibility = View.VISIBLE
                if (storage.isPlaying) {
                    binding.ivMusic.setImageResource(R.drawable.music_on)
                } else {
                    binding.ivMusic.setImageResource(R.drawable.music_off)
                }
                settingsOpened = true
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
        binding.ivMusic.clicks().debounce(0).onEach {
            if ((storage.isPlaying)) {
                MyApp.media.pause()
                storage.isPlaying = false
                binding.ivMusic.setImageResource(R.drawable.music_off)
            } else {
                MyApp.media.start()
                MyApp.media.isLooping = true
                storage.isPlaying = true
                binding.ivMusic.setImageResource(R.drawable.music_on)
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
        binding.ivUz.clicks().debounce(200).onEach { setLocale(requireActivity(), LanguageEnums.UZ.value) }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
        binding.ivRu.clicks().debounce(200).onEach { setLocale(requireActivity(), LanguageEnums.RU.value) }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
        binding.ivEn.clicks().debounce(200).onEach { setLocale(requireActivity(), LanguageEnums.EN.value) }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
        binding.ivHomeIcon.longClicks().debounce(200).onEach {
            val binding = DialogInfoBinding.inflate(LayoutInflater.from(requireContext()))
            val dialog = createDialog(binding = binding, R.style.TransparentDialog, true)
            dialog.setCancelable(true)
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }
}
