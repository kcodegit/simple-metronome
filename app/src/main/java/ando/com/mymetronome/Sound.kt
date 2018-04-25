package ando.com.mymetronome

import android.media.*
import android.os.SystemClock

/**
 * Created by ando on 2018/04/16.
 */
class Sound(val soundPool: SoundPool, val soundId: Int) {
    fun play(){
        println("Sound played:"+ SystemClock.elapsedRealtime())
        soundPool.play(soundId, 0.5F, 0.5F, 0, 0, 1.0F)
//        val mBufferSize = AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT)
//        val mAudioTrack = AudioTrack.Builder()
//                .setAudioAttributes(AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_MEDIA)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                        .build())
//                .setAudioFormat(AudioFormat.Builder()
//                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
//                        .setSampleRate(44100)
//                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
//                        .build())
//                .setBufferSizeInBytes(mBufferSize)
//                .build()
//        var mSound: Array<Double?> = arrayOfNulls(4410)
//        var mBuffer = arrayListOf<Short>()
//        (0.until(4410)).forEach {
//            mSound[it] = Math.sin((2.0*Math.PI * it/(44100/1500)))
//            mBuffer.add((mSound[it]!!.times(Short.MAX_VALUE)).toShort())
//        }
//
//        mAudioTrack.setVolume(0.2F)
//        mAudioTrack.play()
//        mAudioTrack.write(mBuffer.toShortArray(), 0, mSound.size)
//        mAudioTrack.stop()
//        mAudioTrack.release()
    }
}