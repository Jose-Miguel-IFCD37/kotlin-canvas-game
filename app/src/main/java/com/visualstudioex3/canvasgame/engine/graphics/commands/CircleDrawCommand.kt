package com.visualstudioex3.canvasgame.engine.graphics.commands

import android.graphics.Color
import android.graphics.PointF

data class CircleDrawCommand(
    val position: PointF,
    override val zOrder: Int,
    val radius: Float,
    val scale: Float,
    val color: Color,
    val fill: Boolean
) : IDrawCommand
