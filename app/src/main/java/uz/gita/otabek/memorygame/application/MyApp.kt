package uz.gita.otabek.memorygame.application

import android.app.Application
import android.media.MediaPlayer
import dagger.hilt.android.HiltAndroidApp
import uz.gita.otabek.memorygame.R

@HiltAndroidApp
class MyApp : Application() {
    companion object {
        lateinit var media: MediaPlayer
    }

    override fun onCreate() {
        super.onCreate()
        media = MediaPlayer.create(this, R.raw.music)
    }
}