package com.visualstudioex3.canvasgame.engine.graphics.commands

import android.graphics.Bitmap
import android.graphics.PointF

data class SpriteDrawCommand(
    val position: PointF,
    override val zOrder: Int,
    val rotation: Float,
    val scale: Float,
    val sprite: Bitmap
) : IDrawCommand
