package com.visualstudioex3.canvasgame.game.entities.enemies.factory

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class EnemySpawner: GameObject() {
    private val settings: FactorySettingsData = getService<GameSettings>()!!
        .settings.enemySettings.factorySettings
    private val instances = EnemyPool(settings.maxInstances)

    private var time: Float = Time.getTime()

    override fun onUpdate(deltaTime: Float) {
        val now: Float = Time.getTime()
        if (now - time >= settings.spawnTime) {
            instances.getInstance()
            time = now
        }
    }
}
