package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.BaseEnemy
import com.visualstudioex3.canvasgame.game.services.settings.BulletSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.utils.GameObjectUtils

class PlayerBullet: GameObject(), IEnableState {
    private val settings: BulletSettingsData = getService<GameSettings>()!!
        .settings.playerSettings.bulletsSettings
    private val collider: SpriteCollider

    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(settings.bitmapResourceId)
        }
        collider = addComponent<SpriteCollider>().apply {
            onCollision = { other ->
                if (other is BaseEnemy) {
                    this@PlayerBullet.enable = false
                }
            }
        }
        GameObjectUtils.addSpriteColliderRendererIfDebugEnable(this@PlayerBullet)
    }

    override fun onUpdate(deltaTime: Float) {
        transform.move(y = -(settings.speed * deltaTime))

        if (!RenderManager.camera.getBounds().contains(collider.bounds))
            enable = false
    }
}
