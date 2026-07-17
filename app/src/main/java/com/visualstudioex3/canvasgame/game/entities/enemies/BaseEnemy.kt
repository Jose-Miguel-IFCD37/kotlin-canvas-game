package com.visualstudioex3.canvasgame.game.entities.enemies

import android.graphics.Bitmap
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.components.physics.SpriteCollider
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import kotlin.math.min
import kotlin.random.Random

abstract class BaseEnemy: GameObject(), IEnableState {
    companion object {
        const val BASE_MOVEMENT_SPEED: Float = 3f
    }

    private var sprites = mutableListOf<Bitmap>()
    private val spriteRenderer: SpriteRenderer

    var speed: Float = BASE_MOVEMENT_SPEED

    init {
        addComponent<SpriteCollider>().apply {
            onExitCameraBounds = {
                enable = !(transform.position.y > 0f)
            }
        }
        addComponent<SpriteColliderRenderer>()
        spriteRenderer = addComponent<SpriteRenderer>()
    }

    fun addSprite(resourceId: Int) {
        sprites.add(GameResources.loadBitmap(resourceId))
    }

    override fun onEnable() {
        spriteRenderer.image = sprites.random()
        transform.position = PointF(
            Random.nextDouble( // Random no implementa nextFloat(from, until)
                1.0,
                RenderManager.camera.width - 1.0
            ).toFloat(),
            -spriteRenderer.image?.height!!.toFloat()
        )
        speed = min(0.1f, Random.nextFloat()) * BASE_MOVEMENT_SPEED
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = deltaTime * speed)
    }
}
