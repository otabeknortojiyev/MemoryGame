package uz.gita.otabek.memorygame.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {
    var isPlaying by booleans(true)
    var easyAttempts by ints(1)
    var mediumAttempts by ints(1)
    var hardAttempts by ints(1)
    var count by ints(0)
    var level by strings()
}