package ando.com.mymetronome

import android.media.*
import android.os.SystemClock

class Sound(val soundPool: SoundPool, val soundId: Int) {
    fun play(){
        println("Sound played:"+ SystemClock.elapsedRealtime())
        soundPool.play(soundId, 0.5F, 0.5F, 0, 0, 1.0F)
    }
}