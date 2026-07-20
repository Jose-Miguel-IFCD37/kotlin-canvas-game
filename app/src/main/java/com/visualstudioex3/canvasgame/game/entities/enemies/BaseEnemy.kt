package com.visualstudioex3.canvasgame.game.entities.enemies

import android.graphics.Bitmap
import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.extensions.BitmapExtensions.Companion.getSize
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.player.PlayerBullet
import com.visualstudioex3.canvasgame.game.entities.scorer.GameScore
import com.visualstudioex3.canvasgame.game.services.explossion.ExplossionFactory
import com.visualstudioex3.canvasgame.game.services.settings.EnemySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import kotlin.random.Random

abstract class BaseEnemy : GameObject(), IEnemy {
    private val settings: EnemySettingsData = getService<GameSettings>()!!
        .settings.enemySettings
    private val explossionFactory = getService<ExplossionFactory>()!!
    private val renderer = addComponent<SpriteRenderer>()
    private val collider = addComponent<SpriteCollider>().apply {
        addComponent<SpriteColliderRenderer>().apply {
            onCollision = { other ->
                if (other is PlayerBullet) {
                    scorer.addPoints(points)
                    explossionFactory.explode(this@BaseEnemy.transform.position)
                    this@BaseEnemy.enable = false
                }
            }
        }
    }
    private val scorer = SceneManager.scene.gameObjects
        .first { it is GameScore } as GameScore
    abstract val points: Int

    private var sprites = mutableListOf<Bitmap>()

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
        transform.move(y = deltaTime * settings.speed)

        if (!RenderManager.camera.getBounds().intersect(collider.bounds)) {
            if (transform.position.y > 0) {
                enable = false
            }
        }
    }
}
