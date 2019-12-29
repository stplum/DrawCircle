package com.example.drawcircle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
    private val paint: Paint = Paint()
    private val backingBitmap: Bitmap = Bitmap.createBitmap(1080, 2060, Bitmap.Config.ARGB_8888)
    private val offsetY: Float = 240f                       // Status Bar + Title Bar (72+168)
    private var strokeWidth: Float = 10f
    private var radius: Float = 30f

    init {
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = strokeWidth
        paint.color = Color.argb(255, 255, 0, 0)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(backingBitmap, 0f, 0f, paint)
    }

    fun drawColorfulCircle(cx: Float, cy: Float) {
        val canvas = Canvas(backingBitmap)
        canvas.drawCircle(cx, cy - offsetY, radius, paint)
    }

    fun changeRadius(operator: Int) {
        when (operator) {
            0 -> radius -= 10f
            1 -> radius += 10f
        }
        if (radius < 1f) radius = 1f                        // 下限リミット処理
    }
}
