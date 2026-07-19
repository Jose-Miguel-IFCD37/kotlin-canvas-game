package com.visualstudioex3.canvasgame.game.entities.enemies

import android.graphics.Bitmap
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.extensions.BitmapExtensions.Companion.getSize
import com.visualstudioex3.canvasgame.game.entities.player.PlayerBullet
import kotlin.random.Random

abstract class BaseEnemy : GameObject(), IEnableState {
    private var sprites = mutableListOf<Bitmap>()
    private val renderer = addComponent<SpriteRenderer>()
    private val collider = addComponent<SpriteCollider>().apply {
        addComponent<SpriteColliderRenderer>().apply {
            onCollision = { other ->
                if (other is PlayerBullet)
                    this@BaseEnemy.enable = false
            }
        }
    }

    val speed: Float = 3f

    fun addSprite(resourceId: Int) {
        sprites.add(GameResources.loadBitmap(resourceId))
    }

    override fun onEnable() {
        renderer.image = sprites.random()
        transform.position = PointF(
            Random.nextDouble( // Random no implementa nextFloat(from, until)
                1.0,
                RenderManager.camera.width - 1.0
            ).toFloat(),
            -renderer.image?.getSize()?.y!!
        )
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = deltaTime * speed)

        if (!RenderManager.camera.getBounds().intersect(collider.bounds)) {
            if (transform.position.y > 0) {
                enable = false
            }
        }
    }
}
