package com.visualstudioex3.canvasgame.engine.graphics.processors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.extensions.PointFExtensions.toScreenCoordinates
import com.visualstudioex3.canvasgame.engine.graphics.commands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.commands.SpriteDrawCommand

class SpriteDrawProcessor: IDrawProcessor {
    private val paint: Paint = Paint().apply {
        isAntiAlias = true
    }

    override fun process(canvas: Canvas, command: IDrawCommand) {
        command as SpriteDrawCommand

        val screenPosition: PointF = command.position.toScreenCoordinates()
        val spriteCenteredPosition = PointF(
            screenPosition.x - (command.sprite.width / 2f),
            screenPosition.y - (command.sprite.height / 2f)
        )

        if (command.rotation != 0f)
            canvas.rotate(
                command.rotation,
                screenPosition.x,
                screenPosition.y
            )

        if (command.scale != 1f)
            canvas.scale(
                command.scale,
                command.scale,
                screenPosition.x,
                screenPosition.y
            )

        canvas.drawBitmap(
            command.sprite,
            spriteCenteredPosition.x,
            spriteCenteredPosition.y,
            paint
        )
    }
}
