package com.visualstudioex3.canvasgame.engine

import android.graphics.PointF
import androidx.core.graphics.plus

class Transform {
    var position = PointF(0f, 0f)
    var rotation: Float = 0f
    var scale: Float = 1f

    fun move(x: Float = 0f, y: Float = 0f) {
        position += PointF(x, y)
    }

    fun translate(x: Float = position.x, y: Float = position.y) {
        position = PointF(x, y)
    }
}
