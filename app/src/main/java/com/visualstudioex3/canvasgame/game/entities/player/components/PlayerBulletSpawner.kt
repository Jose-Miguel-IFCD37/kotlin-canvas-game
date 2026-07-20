package com.visualstudioex3.canvasgame.game.entities.player.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.game.entities.player.PlayerBulletPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class PlayerBulletSpawner(
    override val gameObject: GameObject
): IComponent {
    private val settings: FactorySettingsData = gameObject.getService<GameSettings>()!!
        .settings.playerSettings.bulletsSettings.factorySettings
    private val instances = PlayerBulletPool(settings.maxInstances)

    private var time: Float = Time.getTime()
    override var enable: Boolean = true

    override fun update(deltaTime: Float) {
        val now: Float = Time.getTime()
        if (now - time >= settings.spawnTime) {
            instances.getInstance().apply {
                this?.transform?.position = gameObject.transform.position
            }
            time = now
        }
    }
}
