package com.visualstudioex3.canvasgame.engine.graphics.drawprocessors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.SpriteDrawCommand

class SpriteDrawProcessor: IDrawProcessor {
    private val paint: Paint = Paint().apply {
        isAntiAlias = true
    }

    override fun process(canvas: Canvas, command: IDrawCommand) {
        command as SpriteDrawCommand

        val screenPosition: PointF = GameRender.screen.toScreenCoordinates(command.position)
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
