package com.visualstudioex3.canvasgame.engine.graphics.drawprocessors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.RectDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand

class RectDrawProcessor: IDrawProcessor {
    override fun process(
        canvas: Canvas,
        command: IDrawCommand
    ) {
        command as RectDrawCommand

        if (!command.rect.isEmpty) {
            val rect: RectF =
                RenderManager.camera.toScreenCoordinates(
                    command.rect
                )

            if (command.scale != 1f)
                canvas.scale(
                    command.scale,
                    command.scale,
                    rect.centerX(),
                    rect.centerY()
                )

            canvas.drawRect(
                rect,
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
}
