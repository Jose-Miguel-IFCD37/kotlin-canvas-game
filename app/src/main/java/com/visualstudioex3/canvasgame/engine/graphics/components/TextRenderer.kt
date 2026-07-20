package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
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

    override fun update(deltaTime: Float) {
    }

    override fun draw() {
        RenderManager.addDrawCommand(
            TextDrawCommand(
                gameObject.transform.position,
                color,
                fontSize,
                align,
                text
            )
        )
    }
}
