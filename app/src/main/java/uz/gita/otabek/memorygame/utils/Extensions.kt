package uz.gita.otabek.memorygame.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import uz.gita.otabek.memorygame.R
import uz.gita.otabek.memorygame.data.models.CardData
import java.util.Locale

fun ImageView.openCard(finishAnim: () -> Unit = {}) {
    this.animate().setDuration(500).rotationY(89f).withEndAction {
        this.setImageResource((tag as CardData).imageRes)
        this.rotationY = -91f
        this.animate().setDuration(500).rotationY(0f).withEndAction {
            finishAnim.invoke()
        }.start()
    }.start()
}


fun ImageView.closeCard(finishAnim: () -> Unit = {}) {
    this.animate().setDuration(500).rotationY(-91f).withEndAction {
        this.setImageResource(R.drawable.green_button)
        this.rotationY = 89f
        this.animate().setDuration(500).rotationY(0f).withEndAction {
            finishAnim.invoke()
        }.start()
    }.start()
}


fun ImageView.hideCard(finishAnim: () -> Unit = {}) {
    this.animate().setDuration(500).scaleX(0f).scaleY(0f).withEndAction {
        this.visibility = View.GONE
        finishAnim.invoke()
    }.start()
}

fun setLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val resources = context.resources
    val config = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
    if (context is Activity) {
        context.recreate()
    }
}

fun Fragment.createDialog(
    binding: ViewBinding, @StyleRes styleRes: Int, cancelable: Boolean = false
): Dialog {
    val dialog = Dialog(requireContext(), styleRes)
    dialog.setContentView(binding.root)
    dialog.show()
    dialog.window?.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
    )
    dialog.setCancelable(cancelable)

    return dialog
}