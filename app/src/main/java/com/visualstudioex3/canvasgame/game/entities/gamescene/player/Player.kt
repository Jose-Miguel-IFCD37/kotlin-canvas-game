package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.input.components.InputTouch
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.IEnemy
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.components.PlayerBulletSpawner
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.components.PlayerTemporalInvulnerability
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerSettingsData
import com.visualstudioex3.canvasgame.game.utils.GameObjectUtils

class Player : GameObject() {
    private val settings: PlayerSettingsData = getRequiredService<GameSettings>()
        .settings.playerSettings
    private val gameObserver = getRequiredService<GameObserver>()
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
                    this@Player.enable = false
                    gameObserver.notifyEvent(
                        GameEvents.PlayerDead(this@Player)
                    )
                }
            }
            GameObjectUtils.addSpriteColliderRendererIfDebugEnable(this@Player)
        }
        addComponent<PlayerBulletSpawner>()
    }

    override fun onEnable() {
        invulnerability.enable = true
        transform.translate(
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
                deltaTime * settings.speed
            )
        )
    }
}
