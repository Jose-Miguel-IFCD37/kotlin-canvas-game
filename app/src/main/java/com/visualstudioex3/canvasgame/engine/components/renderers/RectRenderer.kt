package com.visualstudioex3.canvasgame.engine.components.renderers

import android.graphics.Color
import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.div
import androidx.core.graphics.minus
import androidx.core.graphics.plus
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Transform
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.RectDrawCommand

class RectRenderer(
    override val gameObject: GameObject
) : IRenderer {
    var size = PointF()
    var color = Color()
    var fill: Boolean = true

    override fun draw() {
        if (size != PointF()) {
            val transform: Transform = gameObject.transform
            val leftTop: PointF = transform.position - (size / 2f)
            val rightBottom: PointF = leftTop + size

            GameRender.addDrawCommand(
                RectDrawCommand(
                    RectF(
                        leftTop.x,
                        leftTop.y,
                        rightBottom.x,
                        rightBottom.y,
                    ),
                    transform.scale,
                    color,
                    fill
                )
            )
        }
    }
}
