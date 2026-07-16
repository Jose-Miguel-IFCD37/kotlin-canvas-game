package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.RectDrawCommand

class Primitives {
    private lateinit var _canvas: Canvas

    fun setCanvas(canvas: Canvas) {
        _canvas = canvas
    }

    fun drawRect(
        rect: RectF,
        scale: Float = 1f,
        color: Color,
        fill: Boolean = true
    ) {
        RenderManager.addDrawCommand(
            RectDrawCommand(rect, scale, color, fill)
        )
    }
}
