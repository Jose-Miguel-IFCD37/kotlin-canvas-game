package com.visualstudioex3.canvasgame.engine

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import com.visualstudioex3.canvasgame.engine.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.drawcommands.SpriteDrawCommand
import com.visualstudioex3.canvasgame.engine.drawcommands.TextDrawCommand
import com.visualstudioex3.canvasgame.engine.drawprocessors.SpriteDrawProcessor
import com.visualstudioex3.canvasgame.engine.drawprocessors.TextDrawProcessor

class GameRender(
    private val surfaceHolder: SurfaceHolder
) {
    private var canvas: Canvas? = null
    private val commands = ArrayDeque<IDrawCommand>()
    private val spriteDrawProcessor: SpriteDrawProcessor? = null
    private val textDrawProcessor: TextDrawProcessor? = null

    val screen = Screen(surfaceHolder)

    fun addCommand(command: IDrawCommand) {
        commands.add(command)
    }

    fun draw() {
        canvas = surfaceHolder.lockCanvas()

        synchronized(surfaceHolder) {
            canvas?.drawColor(Color.BLACK)

            while (commands.isNotEmpty()) {
                // TODO: Replace by draw processor factory:
                when (val command: IDrawCommand = commands.removeFirst()) {
                    is SpriteDrawCommand -> {
                        (spriteDrawProcessor ?: SpriteDrawProcessor(canvas!!, screen))
                            .process(command)
                    }
                    is TextDrawCommand -> {
                        (textDrawProcessor ?: TextDrawProcessor(canvas!!, screen))
                            .process(command)
                    }
                }
            }
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }
}
