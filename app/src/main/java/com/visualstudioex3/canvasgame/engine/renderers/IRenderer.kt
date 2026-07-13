package com.visualstudioex3.canvasgame.engine.renderers

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.visualstudioex3.canvasgame.engine.GameObject

interface IRenderer {
    val gameObject: GameObject

    fun DrawScope.draw()
}
