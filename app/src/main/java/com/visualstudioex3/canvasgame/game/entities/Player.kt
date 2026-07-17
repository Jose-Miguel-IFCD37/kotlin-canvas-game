package com.visualstudioex3.canvasgame.game.entities

import android.graphics.PointF
import android.util.Log
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
    private var xTargetPosition: Float = 0f
    private var movementSpeed: Float = 3f

    init {
        addComponent<InputTouch>().onTap = { offset ->
            xTargetPosition = offset.x
            Log.d("Player::onTap", "$offset")
        }
        addComponent<SpriteCollider>()
        addComponent<SpriteColliderRenderer>()
        addComponent<SpriteRenderer>()
            .image = GameResources.loadBitmap(R.drawable.player_ship)

        transform.position = PointF(
            RenderManager.camera.width / 2,
            RenderManager.camera.height - 1f
        )

        xTargetPosition = transform.position.x

        Log.d("Player::init", "${transform.position}")
    }

    override fun onUpdate(deltaTime: Float) {
        transform.translate(
            lerp(
                transform.position.x,
                xTargetPosition,
                deltaTime * movementSpeed
            )
        )
    }
}
