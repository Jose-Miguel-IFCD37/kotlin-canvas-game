package com.visualstudioex3.canvasgame.game.entities.enemies.factory

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Timer
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class EnemySpawner: GameObject() {
    private val settings: FactorySettingsData = getService<GameSettings>()!!
        .settings.enemySettings.factorySettings
    private val instances = EnemyPool(settings.maxInstances)
    private val timer = Timer().apply {
        interval = settings.spawnTime
        onTime = {
            instances.getInstance()
        }
    }

    override fun onUpdate(deltaTime: Float) {
        timer.update()
    }
}
