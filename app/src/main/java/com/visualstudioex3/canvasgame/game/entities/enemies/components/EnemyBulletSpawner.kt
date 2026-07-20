package com.visualstudioex3.canvasgame.game.entities.enemies.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.Timer
import com.visualstudioex3.canvasgame.game.entities.enemies.EnemyBulletPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class EnemyBulletSpawner(
    override val gameObject: GameObject
): IComponent {
    private val settings: FactorySettingsData = gameObject.getService<GameSettings>()!!
        .settings.enemySettings.enemyShipSettings.bulletsSettings.factorySettings
    private val instances = EnemyBulletPool(settings.maxInstances)
    private val timer = Timer().apply {
        interval = settings.spawnTime
        onTime = {
            instances.getInstance()!!.apply {
                transform.position = gameObject.transform.position
            }
        }
    }

    override var enable: Boolean = true

    override fun update(deltaTime: Float) {
        timer.update()
    }
}
