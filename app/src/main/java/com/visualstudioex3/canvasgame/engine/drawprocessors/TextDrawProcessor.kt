package com.visualstudioex3.canvasgame.engine.drawprocessors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.Screen
import com.visualstudioex3.canvasgame.engine.drawcommands.TextDrawCommand

class TextDrawProcessor(
    override val canvas: Canvas,
    override val screen: Screen
) : IDrawProcessor<TextDrawCommand> {
    override fun process(command: TextDrawCommand) {
        val screenPosition: PointF = screen.toScreenCoordinates(command.position)
        val paint: Paint = Paint().apply {
            color = command.color.toArgb()
            textSize = command.fontSize
            isAntiAlias = true
        }

        canvas.drawText(
            command.text,
            screenPosition.x,
            screenPosition.y,
            paint
        )
    }
}
