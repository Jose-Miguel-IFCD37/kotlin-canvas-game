package com.visualstudioex3.canvasgame.engine.drawprocessors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.Screen
import com.visualstudioex3.canvasgame.engine.drawcommands.IDrawCommand

interface IDrawProcessor {
    fun process(canvas: Canvas, command: IDrawCommand)
}
