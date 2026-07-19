package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import com.visualstudioex3.canvasgame.engine.graphics.commands.RectDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.SpriteDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextDrawCommand
import androidx.core.graphics.withSave

class DrawProcessorFactory {
    private val processors = hashMapOf(
        SpriteDrawCommand::class.java to SpriteDrawProcessor(),
        TextDrawCommand::class.java to TextDrawProcessor(),
        RectDrawCommand::class.java to RectDrawProcessor(),
        CircleDrawProcessor::class.java to CircleDrawProcessor(),
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
