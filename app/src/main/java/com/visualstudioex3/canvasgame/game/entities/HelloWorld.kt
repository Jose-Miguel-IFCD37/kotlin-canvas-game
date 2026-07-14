package com.visualstudioex3.canvasgame.game.entities

import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.renderers.TextRenderer
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import com.visualstudioex3.canvasgame.engine.graphics.Screen

class HelloWorld: GameObject() {
    init {
        renderer = TextRenderer(this)

        (renderer as TextRenderer).let {
            it.text = "Hello, World!"
            it.color = Color.GREEN.toColor()
            it.fontSize = 48f
        }

        transform.position = PointF(1.5f, 3f)
    }

    override fun onUpdate() {
        transform.move(3f, 5f)
        Log.d(
            "hello_world::onUpdate",
            //"${GameRender.screen.width}x${GameRender.screen.height}"
            "${transform.position}"
        )
    }
}
