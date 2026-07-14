package com.visualstudioex3.canvasgame.engine.components.renderers

import android.graphics.Bitmap
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import com.visualstudioex3.canvasgame.engine.Transform
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.SpriteDrawCommand

class SpriteRenderer(
    override val gameObject: GameObject
) : IRenderer {
    var image: Bitmap? = null

    override fun draw() {
        if (image != null) {
            val transform: Transform = gameObject.transform

            GameRender.addDrawCommand(
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
