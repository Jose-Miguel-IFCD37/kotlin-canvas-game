package com.visualstudioex3.canvasgame.engine.graphics.drawcommands

import android.graphics.Color
import android.graphics.PointF

data class TextDrawCommand(
    val position: PointF,
    val color: Color,
    val fontSize: Float,
    val text: String
) : IDrawCommand
