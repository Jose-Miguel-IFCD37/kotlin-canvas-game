package com.visualstudioex3.canvasgame.engine.graphics.drawprocessors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.RectDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.SpriteDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextDrawCommand
import androidx.core.graphics.withSave

class DrawProcessorFactory {
    private val processors = hashMapOf(
        SpriteDrawCommand::class.java to SpriteDrawProcessor(),
        TextDrawCommand::class.java to TextDrawProcessor(),
        RectDrawCommand::class.java to RectDrawProcessor(),
    )

    fun process(canvas: Canvas, command: IDrawCommand) {
        val processor = processors[command.javaClass]
            ?: error("No se encuentra procesador de dibujado para el comando " +
                    "${command.javaClass.name}")

        canvas.withSave {
            processor.process(this, command)
        }
    }
}
