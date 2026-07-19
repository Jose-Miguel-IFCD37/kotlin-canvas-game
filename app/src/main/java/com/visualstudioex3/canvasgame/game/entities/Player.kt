package com.visualstudioex3.canvasgame.game.entities

import android.graphics.PointF
import androidx.compose.ui.util.lerp
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.components.input.InputTouch
import com.visualstudioex3.canvasgame.engine.components.physics.SpriteCollider
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class Player : GameObject() {
    private var targetPosition: Float = 0f
    private var movementSpeed: Float = 3f

    init {
        addComponent<InputTouch>().apply {
            onTap = { offset ->
                targetPosition = offset.x
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
            lerp(
                transform.position.x,
                targetPosition,
                deltaTime * movementSpeed
            )
        )
    }
}
