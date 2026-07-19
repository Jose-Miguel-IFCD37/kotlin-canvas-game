package com.visualstudioex3.canvasgame.game.entities

import android.graphics.PointF
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.input.components.InputTouch
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class Player : GameObject() {
    private var targetPosition: Float = 0f
    private var movementSpeed: Float = 3f

    init {
        addComponent<InputTouch>().apply {
            onTap = { offset ->
                targetPosition = MathF.clamp(
                    offset.x,
                    1f,
                    RenderManager.camera.width - 1f
                )
            }
        }
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(R.drawable.player_ship)
        }
        addComponent<SpriteCollider>()
        addComponent<SpriteColliderRenderer>()

        transform.position = PointF(
            RenderManager.camera.width / 2,
            RenderManager.camera.height - 1f
        )

        targetPosition = transform.position.x
    }

    override fun onUpdate(deltaTime: Float) {
        transform.translate(
            MathF.lerp(
                transform.position.x,
                targetPosition,
                deltaTime * movementSpeed
            )
        )
    }
}
