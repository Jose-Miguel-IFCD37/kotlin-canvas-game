package com.visualstudioex3.canvasgame.game.entities.gamescene.player.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.Timer
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.services.PlayerBulletPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class PlayerBulletSpawner(
    override val gameObject: GameObject
): IComponent {
    private val settings: FactorySettingsData = gameObject.getService<GameSettings>()!!
        .settings.playerSettings.bulletsSettings.factorySettings
    private val instances = PlayerBulletPool(settings.maxInstances)
    private val timer = Timer().apply {
        interval = settings.spawnTime
        onTime = {
            instances.getInstance()?.apply {
                transform.position = gameObject.transform.position
            }
        }
    }

    override var enable: Boolean = true

    override fun update(deltaTime: Float) {
        timer.update()
    }
}
