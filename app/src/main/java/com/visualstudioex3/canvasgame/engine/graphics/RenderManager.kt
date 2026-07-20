package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.view.SurfaceHolder
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextAlign
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.processors.DrawProcessorFactory

class RenderManager(
    private val surfaceHolder: SurfaceHolder
) {
    companion object {
        private lateinit var _camera: Camera
        private val commands = ArrayDeque<IDrawCommand>()

        val camera: Camera
            get() = _camera

        var showFPSCounter: Boolean = false

        fun addDrawCommand(command: IDrawCommand) {
            commands.add(command)
        }
    }

    private val drawProcessors = DrawProcessorFactory()

    init {
        _camera = Camera(surfaceHolder)
    }

    fun present() {
        val canvas: Canvas = surfaceHolder.lockCanvas()

        if (showFPSCounter)
            addFPSCounterCommand()

        synchronized(surfaceHolder) {
            canvas.drawColor(Color.BLACK)

            while (commands.isNotEmpty())
                drawProcessors.process(canvas, commands.removeFirst())
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    private fun addFPSCounterCommand() {
        addDrawCommand(
            TextDrawCommand(
                position = PointF(camera.width, 0.5f),
                color = Color.YELLOW.toColor(),
                fontSize = 48f,
                align = TextAlign.Right,
                text = "%,.2ffps".format(Time.framesPerSecond)
            )
        )
    }
}
