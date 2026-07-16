package com.visualstudioex3.canvasgame.game.entities

import android.graphics.PointF
import android.util.Log
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.components.physics.SpriteCollider
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class Player : GameObject() {
    init {
        addComponent<SpriteCollider>()
        addComponent<SpriteColliderRenderer>()
        addComponent<SpriteRenderer>()
            .image = GameResources.loadBitmap(R.drawable.player_ship)

        transform.position = PointF(
            RenderManager.camera.width / 2,
            RenderManager.camera.height - 1f
        )

        Log.d("Player::init", "${transform.position}")
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
