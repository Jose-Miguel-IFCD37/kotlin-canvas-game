package com.visualstudioex3.canvasgame.game.entities.enemies

import com.visualstudioex3.canvasgame.game.services.settings.AsteroidSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class Asteroid: BaseEnemy() {
    private val settings: AsteroidSettingsData = getService<GameSettings>()!!
        .settings.enemySettings.asteroidSettings

    init {
        settings.bitmapResourceIds.forEach {
            addSprite(it)
        }
    }

    override fun onUpdate(deltaTime: Float) {
        super.onUpdate(deltaTime)

        transform.rotation += deltaTime * settings.rotation
    }
}
