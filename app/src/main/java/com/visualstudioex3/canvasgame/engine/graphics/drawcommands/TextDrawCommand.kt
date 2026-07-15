package com.visualstudioex3.canvasgame.engine.graphics.drawcommands

import android.graphics.Color
import android.graphics.PointF

@Suppress("unused")
enum class TextAlign {
    Left,
    Right,
    Center,
}

data class TextDrawCommand(
    val position: PointF,
    val color: Color,
    val fontSize: Float,
    val align: TextAlign,
    val text: String
) : IDrawCommand
