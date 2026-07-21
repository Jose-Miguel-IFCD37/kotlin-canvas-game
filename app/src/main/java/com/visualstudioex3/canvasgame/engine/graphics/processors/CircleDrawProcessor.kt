package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.extensions.PointFExtensions.toScreenCoordinates
import com.visualstudioex3.canvasgame.engine.graphics.commands.CircleDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand

class CircleDrawProcessor: IDrawProcessor {
    override fun process(
        canvas: Canvas,
        command: IDrawCommand
    ) {
        command as CircleDrawCommand

        val screenPosition: PointF = command.position.toScreenCoordinates()
        val screenRadius: Float = PointF(command.radius, 0f)
            .toScreenCoordinates().x

        if (command.scale != 1f)
            canvas.scale(
                command.scale,
                command.scale,
                screenPosition.x,
                screenPosition.y
            )

        canvas.drawCircle(
            screenPosition.x,
            screenPosition.y,
            screenRadius,
            Paint().apply {
                style = if (command.fill)
                    Paint.Style.FILL
                else
                    Paint.Style.STROKE
                color = command.color.toArgb()
                isAntiAlias = true
            }
        )
    }
}
