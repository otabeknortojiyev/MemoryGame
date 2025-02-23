package uz.gita.otabek.memorygame.presentation.screens.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private var _binding: ScreenSplashBinding? = null
    private val binding: ScreenSplashBinding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ScreenSplashBinding.bind(view)
        val handler = Handler(Looper.getMainLooper())
        val upAnim = android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.anim_scale_up)
        val downAnim = android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.anim_scale_down)
        handler.postDelayed({
            binding.tvSplashLogo.startAnimation(downAnim)
            handler.postDelayed({
                binding.tvSplashLogo.visibility = View.INVISIBLE
                binding.ivSplashBg.setBackgroundResource(R.drawable.miyyajon)
                binding.ivSplashBg.startAnimation(upAnim)
                handler.postDelayed({
                    findNavController().navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
                }, 500)
            }, 500)
        }, 1500)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}