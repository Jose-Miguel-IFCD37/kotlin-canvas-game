package com.visualstudioex3.canvasgame.game.entities.enemies

import com.visualstudioex3.canvasgame.game.entities.enemies.components.EnemyBulletSpawner
import com.visualstudioex3.canvasgame.game.services.settings.EnemyShipSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class EnemyShip: BaseEnemy() {
    private val settings: EnemyShipSettingsData = getService<GameSettings>()!!
        .settings.enemySettings.enemyShipSettings
    override val points: Int = settings.points

    init {
        addComponent<EnemyBulletSpawner>()
        settings.bitmapResourceIds.forEach {
            addSprite(it)
        }
    }
}
