package com.visualstudioex3.canvasgame.engine.graphics.commands

import android.graphics.Color
import android.graphics.RectF

data class RectDrawCommand(
    val rect: RectF,
    val scale: Float,
    val color: Color,
    val fill: Boolean
): IDrawCommand
