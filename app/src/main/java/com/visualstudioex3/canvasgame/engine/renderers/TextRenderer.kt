package com.visualstudioex3.canvasgame.engine.renderers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import com.visualstudioex3.canvasgame.engine.GameEngine
import com.visualstudioex3.canvasgame.engine.GameObject

class TextRenderer(
    override val gameObject: GameObject
) : IRenderer {
    var text: String = ""

    override fun DrawScope.draw() {
        scale(gameObject.transform.scale, gameObject.transform.position) {
            drawText(
                composeTextLayoutResult(),
                color = Color.White,
                topLeft = gameObject.transform.position
            )
        }
    }

    private fun composeTextLayoutResult(): TextLayoutResult =
        GameEngine.globalSettings.textMeasurer.measure(
            text = text,
            style = GameEngine.globalSettings.materialTextStyle
        )
}
