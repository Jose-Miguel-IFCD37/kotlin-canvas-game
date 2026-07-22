package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Transform
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextAlign
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextDrawCommand

class TextRenderer(
    override val gameObject: GameObject
) : IRenderer {
    override var enable: Boolean = true
    var text: String = ""
    var color: Color = Color.WHITE.toColor()
    var fontSize: Float = 24f
    var align: TextAlign = TextAlign.Left
    var maxWidth: Float = 0f

    override fun update(deltaTime: Float) {
    }

    override fun draw() {
        val transform: Transform = gameObject.transform

        RenderManager.addDrawCommand(
            TextDrawCommand(
                transform.position,
                transform.zOrder,
                transform.scale,
                color,
                fontSize,
                align,
                text,
                maxWidth
            )
        )
    }
}
