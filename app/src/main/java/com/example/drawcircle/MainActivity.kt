package com.example.drawcircle

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundSelect: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ボタン押下処理の登録
        minusButton.setOnClickListener {
            myView.changeRadius(0)
            playSE(soundSelect)
        }
        plusButton.setOnClickListener {
            myView.changeRadius(1)
            playSE(soundSelect)
        }

        // SE音の準備
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()
        soundSelect = soundPool.load(this, R.raw.select01, 1)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN,
                MotionEvent.ACTION_MOVE,
                MotionEvent.ACTION_UP -> {
                    coordinateLabel.text = "( %d, %d )".format(event.x.toInt(), event.y.toInt())
                    myView.drawColorfulCircle(event.x, event.y)
                    myView.invalidate()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun playSE(sound: Int) {
        soundPool.play(sound, 1f, 1f, 0, 0, 1f)
    }
}
