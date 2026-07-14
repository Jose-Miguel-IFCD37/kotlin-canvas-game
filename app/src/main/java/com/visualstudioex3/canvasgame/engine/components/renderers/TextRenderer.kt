package com.visualstudioex3.canvasgame.engine.components.renderers

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextDrawCommand

class TextRenderer(
    override val gameObject: GameObject
) : IRenderer {
    var text: String = ""
    var color: Color = Color.WHITE.toColor()
    var fontSize: Float = 24f

    override fun draw() {
        GameRender.addDrawCommand(
            TextDrawCommand(
                gameObject.transform.position,
                color,
                fontSize,
                text
            )
        )
    }
}
