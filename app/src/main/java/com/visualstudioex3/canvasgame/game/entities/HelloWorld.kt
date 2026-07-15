package com.visualstudioex3.canvasgame.game.entities

import android.graphics.Color
import android.graphics.PointF
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.renderers.TextRenderer

class HelloWorld: GameObject() {
    init {
        renderer = TextRenderer(this)

        (renderer as TextRenderer).let {
            it.text = "Hello, World!"
            it.color = Color.GREEN.toColor()
            it.fontSize = 48f
        }

        transform.position = PointF(0f, 0.5f)
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
