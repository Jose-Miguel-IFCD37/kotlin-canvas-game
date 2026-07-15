package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.view.SurfaceHolder
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameTime
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextAlign
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawprocessors.DrawProcessorFactory

class GameRender(
    private val surfaceHolder: SurfaceHolder
) {
    companion object {
        private lateinit var _screen: Screen
        private val commands = ArrayDeque<IDrawCommand>()

        val screen: Screen
            get() = _screen
        val primitives = Primitives()

        var showFPSCounter: Boolean = false

        fun addDrawCommand(command: IDrawCommand) {
            commands.add(command)
        }
    }

    private val drawProcessors = DrawProcessorFactory()

    init {
        _screen = Screen(surfaceHolder)
    }

    fun present() {
        val canvas: Canvas = surfaceHolder.lockCanvas()

        if (showFPSCounter)
            addFPSCounterCommand()

        synchronized(surfaceHolder) {
            primitives.setCanvas(canvas)

            canvas.drawColor(Color.BLACK)

            while (commands.isNotEmpty())
                drawProcessors.process(canvas, commands.removeFirst())
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    private fun addFPSCounterCommand() {
        addDrawCommand(
            TextDrawCommand(
                position = PointF(screen.width - 1f, 0.5f),
                color = Color.YELLOW.toColor(),
                fontSize = 48f,
                align = TextAlign.Right,
                text = "%,.2ffps".format(GameTime.framesPerSecond)
            )
        )
    }
}
