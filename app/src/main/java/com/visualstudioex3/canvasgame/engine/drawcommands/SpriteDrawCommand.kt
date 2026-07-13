package com.visualstudioex3.canvasgame.engine.drawcommands

import android.graphics.Bitmap
import android.graphics.PointF

data class SpriteDrawCommand(
    val position: PointF,
    val rotation: Float,
    val scale: Float,
    val sprite: Bitmap
) : IDrawCommand
