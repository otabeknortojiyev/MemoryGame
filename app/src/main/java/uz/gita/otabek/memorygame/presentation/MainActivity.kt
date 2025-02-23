package uz.gita.otabek.memorygame.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.navigation.AppNavigationHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appNavigationHandler: AppNavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findViewById<FragmentContainerView>(R.id.nav_host_fragment).getFragment<NavHostFragment>().navController
        appNavigationHandler.navigationStack.onEach { it(navController) }.launchIn(lifecycleScope)
    }
}