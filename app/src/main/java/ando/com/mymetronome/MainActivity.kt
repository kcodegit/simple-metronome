package ando.com.mymetronome

import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var startBtn: Button
    lateinit var stopBtn: Button
    lateinit var sound: Sound
    lateinit var bpmTv: TextView
    lateinit var sb: SeekBar

    var h = Handler()
    var runnable: Runnable? = null
    var bpm: Int = 100
    var spanMicros: Long = 0L
    var counter = 0
    var baseTime = 0L
    var timeStamp = 0L
    lateinit var soundPool: SoundPool
    var soundId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        soundId = soundPool.load(this, R.raw.sample01, 1)
        sound = Sound(soundPool, soundId)

        startBtn = findViewById(R.id.start_btn)
        stopBtn = findViewById(R.id.stop_btn)
        sb = findViewById(R.id.seekBar)
        bpmTv = findViewById(R.id.bpm_tv)
        spanMicros = toSpanMicros(bpm)


        startBtn.setOnClickListener{
            play()
            startBtn.isEnabled = false
            stopBtn.isEnabled = true
        }
        stopBtn.setOnClickListener {
            stop()
            startBtn.isEnabled = true
            stopBtn.isEnabled = false
        }

        sb.max = 210
        sb.progress = 60
        sb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                bpmTv.text = (p1 + 40).toString()
                bpm = p1 + 40
                spanMicros = toSpanMicros(bpm)
                println("spanMicros:"+ spanMicros)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        runnable = Runnable {
            timeStamp = System.nanoTime()
            val diff = Math.round(((timeStamp - baseTime)/1000).toFloat())
            if((diff) >= spanMicros) {
                counter++
                println("counter " + counter)
                println("play called:" + SystemClock.elapsedRealtime())
                sound.play()
                baseTime = timeStamp
            }
            h.postDelayed(runnable, 1)
        }
    }

    fun play(){
        sound.play()
        baseTime = System.nanoTime()
        h.post(runnable)
    }

    fun stop(){
        h.removeCallbacks(runnable)
    }

    private fun toSpanMicros(bpm: Int)=Math.round((60*1000*1000/bpm).toFloat()).toLong()
}
