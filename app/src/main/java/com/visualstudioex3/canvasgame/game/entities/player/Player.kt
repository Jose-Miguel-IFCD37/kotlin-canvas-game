package com.visualstudioex3.canvasgame.game.entities.player

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.input.components.InputTouch
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.enemies.BaseEnemy
import com.visualstudioex3.canvasgame.game.entities.enemies.EnemyBullet
import com.visualstudioex3.canvasgame.game.entities.enemies.IEnemy
import com.visualstudioex3.canvasgame.game.entities.player.components.PlayerBulletSpawner
import com.visualstudioex3.canvasgame.game.entities.player.components.PlayerTemporalInvulnerability
import com.visualstudioex3.canvasgame.game.services.explossion.ExplossionFactory
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerSettingsData

class Player : GameObject() {
    private val settings: PlayerSettingsData = getService<GameSettings>()!!
        .settings.playerSettings
    private val explossionFactory = getService<ExplossionFactory>()!!
    private val invulnerability = addComponent<PlayerTemporalInvulnerability>()
    private var targetPosition: Float = 0f

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
            image = GameResources.loadBitmap(settings.bitmapResourceId)
        }
        addComponent<SpriteCollider>().apply {
            onCollision = { other ->
                if (!invulnerability.enable &&
                    other is IEnemy
                ) {
                    explossionFactory.explode(this@Player.transform.position)
                    this@Player.enable = false
                }
            }
        }
        addComponent<SpriteColliderRenderer>()
        addComponent<PlayerBulletSpawner>()

        transform.position = PointF(
            RenderManager.camera.width / 2,
            RenderManager.camera.height - 1f
        )

        targetPosition = transform.position.x
    }

    override fun onEnable() {
        invulnerability.enable = true
    }

    override fun onUpdate(deltaTime: Float) {
        transform.translate(
            MathF.lerp(
                transform.position.x,
                targetPosition,
                deltaTime * settings.speed
            )
        )
    }
}
