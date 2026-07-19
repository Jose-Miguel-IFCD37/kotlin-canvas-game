package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.commands.CircleDrawCommand

class CircleRenderer(
    override val gameObject: GameObject
) : IRenderer {
    override var enable: Boolean = true
    var color = Color.WHITE.toColor()
    var radius: Float =  1f
    var fill: Boolean = true

    override fun update(deltaTime: Float) {
    }

    override fun draw() {
        RenderManager.addDrawCommand(
            CircleDrawCommand(
                gameObject.transform.position,
                radius,
                gameObject.transform.scale,
                color,
                fill
            )
        )
    }
}
