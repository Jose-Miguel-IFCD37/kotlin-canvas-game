package com.visualstudioex3.canvasgame.engine

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import com.visualstudioex3.canvasgame.engine.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.drawcommands.SpriteDrawCommand
import com.visualstudioex3.canvasgame.engine.drawcommands.TextDrawCommand
import com.visualstudioex3.canvasgame.engine.drawprocessors.DrawProcessorFactory
import com.visualstudioex3.canvasgame.engine.drawprocessors.SpriteDrawProcessor
import com.visualstudioex3.canvasgame.engine.drawprocessors.TextDrawProcessor

class GameRender(
    private val surfaceHolder: SurfaceHolder
) {
    companion object {
        private lateinit var _screen: Screen
        private val commands = ArrayDeque<IDrawCommand>()

        val screen: Screen
            get() = _screen

        fun addDrawCommand(command: IDrawCommand) {
            commands.add(command)
        }
    }

    private val drawProcessors = DrawProcessorFactory()

    init {
        _screen = Screen(surfaceHolder)
    }

    fun draw() {
        val canvas: Canvas = surfaceHolder.lockCanvas()

        synchronized(surfaceHolder) {
            canvas.drawColor(Color.BLACK)

            while (commands.isNotEmpty())
                drawProcessors.process(canvas, commands.removeFirst())
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }
}
