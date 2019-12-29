package com.example.drawcircle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            myView.drawColorfulCircle(event.x, event.y)
            myView.invalidate()
        }
        return super.onTouchEvent(event)
    }
}
