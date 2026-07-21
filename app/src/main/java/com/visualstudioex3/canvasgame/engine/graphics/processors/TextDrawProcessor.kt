package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import android.graphics.PointF
import android.text.StaticLayout
import android.text.TextPaint
import com.visualstudioex3.canvasgame.engine.extensions.PointFExtensions.Companion.toScreenCoordinates
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextDrawCommand

class TextDrawProcessor : IDrawProcessor {
    override fun process(canvas: Canvas, command: IDrawCommand) {
        command as TextDrawCommand

        val screenPosition: PointF = command.position.toScreenCoordinates()
        val maxWidth: Int = PointF(
            if (command.maxWidth > 0f)
                command.maxWidth
            else
                RenderManager.camera.getBounds().width(),
            0f
        ).toScreenCoordinates().x.toInt()
        val staticLayout = StaticLayout.Builder
            .obtain(
                command.text,
                0,
                command.text.length,
                TextPaint().apply {
                    color = command.color.toArgb()
                    textSize = command.fontSize
                    isAntiAlias = true
                    textAlign = command.align.value
                },
                maxWidth
            )
            .build()

        canvas.translate(
            screenPosition.x,
            screenPosition.y
        )
        staticLayout.draw(canvas)
    }
}
