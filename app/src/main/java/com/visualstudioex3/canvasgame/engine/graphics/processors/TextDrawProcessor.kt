package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.extensions.PointFExtensions.Companion.toScreenCoordinates
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextDrawCommand

class TextDrawProcessor: IDrawProcessor {
    override fun process(canvas: Canvas, command: IDrawCommand) {
        command as TextDrawCommand

        val screenPosition: PointF = command.position.toScreenCoordinates()
        val paint: Paint = Paint().apply {
            color = command.color.toArgb()
            textSize = command.fontSize
            isAntiAlias = true
            textAlign = command.align.value
        }

        canvas.drawText(
            command.text,
            screenPosition.x,
            screenPosition.y,
            paint
        )
    }
}
