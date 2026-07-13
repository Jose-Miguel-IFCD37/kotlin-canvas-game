package com.visualstudioex3.canvasgame.engine

import androidx.compose.ui.geometry.Offset

class Transform {
    var position = Offset.Zero
    var rotation: Float = 0f
    var scale: Float = 1f

    fun move(x: Float = 0f, y: Float = 0f) = position + Offset(x, y)
}
