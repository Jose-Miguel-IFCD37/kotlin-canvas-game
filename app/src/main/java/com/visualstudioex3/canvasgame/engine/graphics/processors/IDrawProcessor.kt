package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand

interface IDrawProcessor {
    fun process(canvas: Canvas, command: IDrawCommand)
}
