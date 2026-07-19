package com.visualstudioex3.canvasgame.game.entities.player

import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.enemies.BaseEnemy

class PlayerBullet: GameObject(), IEnableState {
    private val speed: Float = 15f
    private val collider: SpriteCollider

    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(R.drawable.player_bullet)
        }
        collider = addComponent<SpriteCollider>().apply {
            onCollision = { other ->
                if (other is BaseEnemy) {
                    this@PlayerBullet.enable = false
                }
            }
        }
        addComponent<SpriteColliderRenderer>()
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = -(speed * deltaTime))

        if (!RenderManager.camera.getBounds().contains(collider.bounds))
            enable = false
    }
}
