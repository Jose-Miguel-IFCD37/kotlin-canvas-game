package com.visualstudioex3.canvasgame.engine.drawprocessors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameRender
import com.visualstudioex3.canvasgame.engine.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.drawcommands.TextDrawCommand

class TextDrawProcessor: IDrawProcessor {
    override fun process(canvas: Canvas, command: IDrawCommand) {
        command as TextDrawCommand

        val screenPosition: PointF = GameRender.screen.toScreenCoordinates(command.position)
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
