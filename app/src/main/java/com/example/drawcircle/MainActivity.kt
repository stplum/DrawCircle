package com.example.drawcircle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        minusButton.setOnClickListener {
            myView.changeRadius(0)
        }
        plusButton.setOnClickListener {
            myView.changeRadius(1)
        }
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
}
