package com.visualstudioex3.canvasgame.game.entities.gamescene.enemies

import android.graphics.Bitmap
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.extensions.BitmapExtensions.getSize
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.PlayerBullet
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.EnemySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.utils.GameObjectUtils
import kotlin.random.Random

abstract class BaseEnemy : GameObject(), IEnemy {
    private val settings: EnemySettingsData = getRequiredService<GameSettings>()
        .settings.enemySettings
    private val gameObserver = getRequiredService<GameObserver>()
    private val renderer = addComponent<SpriteRenderer>()
    private val collider = addComponent<SpriteCollider>().apply {
        onCollision = { other ->
            if (other is PlayerBullet) {
                gameObserver.notifyEvent(GameEvents.EnemyDead(this@BaseEnemy))
                this@BaseEnemy.enable = false
            }
        }

        GameObjectUtils.addSpriteColliderRendererIfDebugEnable(this@BaseEnemy)
    }

    abstract val points: Int

    private var sprites = mutableListOf<Bitmap>()

    fun addSprite(resourceId: Int) {
        sprites.add(GameResources.loadBitmap(resourceId))
    }

    override fun onEnable() {
        renderer.image = sprites.random()
        transform.translate(
            Random.nextDouble( // Random no implementa nextFloat(from, until)
                1.0,
                RenderManager.camera.width - 1.0
            ).toFloat(),
            -renderer.image?.getSize()?.y!!
        )
        collider.sync()
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = deltaTime * settings.speed)

        if (!RenderManager.camera.getBounds().intersect(collider.bounds)) {
            if (transform.position.y > 0) {
                enable = false
            }
        }
    }
}
