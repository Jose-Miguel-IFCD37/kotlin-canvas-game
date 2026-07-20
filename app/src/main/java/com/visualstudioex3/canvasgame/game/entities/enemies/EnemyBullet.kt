package com.visualstudioex3.canvasgame.game.entities.enemies

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.player.Player
import com.visualstudioex3.canvasgame.game.services.settings.BulletSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.utils.GameObjectUtils

class EnemyBullet: GameObject(), IEnemy {
    private val gameSettings = getService<GameSettings>()!!
    private val settings: BulletSettingsData = gameSettings.settings.enemySettings.enemyShipSettings.bulletsSettings
    private val collider: SpriteCollider

    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(settings.bitmapResourceId)
        }
        collider = addComponent<SpriteCollider>().apply {
            onCollision = { other ->
                if (other is Player) {
                    this@EnemyBullet.enable = false
                }
            }
        }
        addComponent<SpriteColliderRenderer>().apply {
            GameObjectUtils.addSpriteColliderRendererIfDebugEnable(this@EnemyBullet)
        }
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = settings.speed * deltaTime)

        if (!RenderManager.camera.getBounds().contains(collider.bounds))
            enable = false
    }
}
