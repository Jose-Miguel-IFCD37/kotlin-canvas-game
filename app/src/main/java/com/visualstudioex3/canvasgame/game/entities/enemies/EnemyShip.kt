package com.visualstudioex3.canvasgame.game.entities.enemies

import com.visualstudioex3.canvasgame.game.services.settings.EnemyShipSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class EnemyShip: BaseEnemy() {
    private val settings: EnemyShipSettingsData = getService<GameSettings>()!!
        .settings.enemySettings.enemyShipSettings

    init {
        settings.bitmapResourceIds.forEach {
            addSprite(it)
        }
    }
}
