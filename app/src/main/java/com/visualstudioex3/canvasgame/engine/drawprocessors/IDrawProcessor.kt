package com.visualstudioex3.canvasgame.engine.drawprocessors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.Screen
import com.visualstudioex3.canvasgame.engine.drawcommands.IDrawCommand

interface IDrawProcessor<in T : IDrawCommand> {
    val canvas: Canvas
    val screen: Screen

    fun process(command: T)
}
