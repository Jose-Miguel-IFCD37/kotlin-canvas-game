package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.services

import com.visualstudioex3.canvasgame.engine.GameService
import com.visualstudioex3.canvasgame.game.entities.GameObserver
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.systems.ScorePointsPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ScorePointsFactory: GameService() {
    private val enemyFactorySettings: FactorySettingsData = getRequiredService<GameSettings>()
        .settings.enemySettings.factorySettings
    private val pool = ScorePointsPool(enemyFactorySettings.maxInstances)

    init {
        getRequiredService<GameObserver>().apply {
            onEnemyDead = { enemy ->
                pool.getInstance()?.apply {
                    show(enemy.transform.position, enemy.points)
                }
            }
        }
    }
}
