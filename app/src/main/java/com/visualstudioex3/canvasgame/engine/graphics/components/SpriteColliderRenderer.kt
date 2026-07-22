package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Color
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Transform
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.commands.RectDrawCommand

class SpriteColliderRenderer(
    override val gameObject: GameObject
) : IRenderer {
    private val spriteCollider = gameObject.getComponent<SpriteCollider>()

    override var enable: Boolean = true
    var color = Color.valueOf(1f, 0f, 0f, 0.25f)

    override fun update(deltaTime: Float) {
    }

    override fun draw() {
        if (spriteCollider != null) {
            val transform: Transform = gameObject.transform

            RenderManager.addDrawCommand(
                RectDrawCommand(
                    spriteCollider.bounds,
                    transform.zOrder,
                    transform.scale,
                    color,
                    true
                )
            )
        }
    }
}
