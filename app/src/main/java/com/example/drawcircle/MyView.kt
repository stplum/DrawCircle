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
    private var count: Int = 0
    private var radius: Float = 30f

    init {
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(backingBitmap, 0f, 0f, paint)
    }

    fun drawColorfulCircle(cx: Float, cy: Float) {
        val canvas = Canvas(backingBitmap)
        setColor(count)
        canvas.drawCircle(cx, cy - offsetY, radius, paint)
    }

    fun changeRadius(operator: Int) {
        when (operator) {
            0 -> radius -= 10f
            1 -> radius += 10f
        }
        if (radius < 1f) radius = 1f                        // 下限リミット処理
    }

    private fun setColor(value: Int) {
        val resolution = 16                                 // RGB個々の分解能
        val currentColor: Int = value % (resolution * 6)    // 入力値を分解能の範囲でリミット
        val hue: Int = currentColor / resolution            // 色相
        val diff: Int = value % resolution                  // 色相中の段階
        var red = 0
        var green = 0
        var blue = 0

        when(hue) {
            0 -> {                                          // 赤～黄
                red = 255
                green = resolution * diff
                blue = 0
            }
            1 -> {                                          // 黄～緑
                red = minOf(255, 256 - resolution * diff)
                green = 255
                blue = 0
            }
            2 -> {                                          // 緑～シアン
                red = 0
                green = 255
                blue = diff*resolution
            }
            3 -> {                                          // シアン～青
                red = 0
                green = minOf(255, 256 - resolution * diff)
                blue = 255
            }
            4 -> {                                          // 青～マゼンタ
                red = diff*resolution
                green = 0
                blue = 255
            }
            5 -> {                                          // マゼンタ～赤
                red = 255
                green = 0
                blue = minOf(255, 256 - resolution * diff)
            }
        }
        paint.color = Color.rgb(red, green, blue)
        count += 1
    }
}
