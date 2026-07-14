package com.visualstudioex3.canvasgame.engine.graphics.drawprocessors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand

interface IDrawProcessor {
    fun process(canvas: Canvas, command: IDrawCommand)
}
