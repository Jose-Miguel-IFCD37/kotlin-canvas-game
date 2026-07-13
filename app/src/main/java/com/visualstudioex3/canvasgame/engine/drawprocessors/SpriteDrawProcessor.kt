package com.visualstudioex3.canvasgame.engine.drawprocessors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.Screen
import com.visualstudioex3.canvasgame.engine.drawcommands.SpriteDrawCommand

class SpriteDrawProcessor(
    override val canvas: Canvas,
    override val screen: Screen
): IDrawProcessor<SpriteDrawCommand> {
    private val paint: Paint = Paint().apply {
        isAntiAlias = true
    }

    override fun process(command: SpriteDrawCommand) {
        val screenPosition: PointF = screen.toScreenCoordinates(command.position)
        val pivot = PointF(
            screenPosition.x - (command.sprite.width / 2f),
            screenPosition.y - (command.sprite.height / 2f)
        )

        if (command.rotation != 0f)
            canvas.rotate(command.rotation, pivot.x, pivot.y)

        if (command.scale != 1f)
            canvas.scale(command.scale, command.scale, pivot.x, pivot.y)

        canvas.drawBitmap(command.sprite, pivot.x, pivot.y, paint)
    }
}
