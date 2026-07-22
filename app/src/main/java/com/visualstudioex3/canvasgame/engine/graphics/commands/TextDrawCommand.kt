package com.visualstudioex3.canvasgame.engine.graphics.commands

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF

@Suppress("unused")
enum class TextAlign(val value: Paint.Align) {
    Left(Paint.Align.LEFT),
    Right(Paint.Align.RIGHT),
    Center(Paint.Align.CENTER),
}

data class TextDrawCommand(
    val position: PointF,
    override val zOrder: Int,
    val scale: Float,
    val color: Color,
    val fontSize: Float,
    val align: TextAlign,
    val text: String,
    val maxWidth: Float
) : IDrawCommand
