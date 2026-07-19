package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Bitmap
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.Transform
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.SpriteDrawCommand

class SpriteRenderer(
    override val gameObject: GameObject
) : IRenderer {
    override var enable: Boolean = true
    var image: Bitmap? = null

    override fun update(deltaTime: Float) {
    }

    override fun draw() {
        if (image != null) {
            val transform: Transform = gameObject.transform

            RenderManager.addDrawCommand(
                SpriteDrawCommand(
                    transform.position,
                    transform.rotation,
                    transform.scale,
                    image!!
                )
            )
        }
    }
}
