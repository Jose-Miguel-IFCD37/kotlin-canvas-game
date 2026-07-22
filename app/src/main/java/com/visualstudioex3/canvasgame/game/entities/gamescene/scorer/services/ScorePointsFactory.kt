package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.services

import com.visualstudioex3.canvasgame.engine.GameService
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.systems.ScorePointsPool
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ScorePointsFactory: GameService(), IEventListener {
    private val enemyFactorySettings: FactorySettingsData = getRequiredService<GameSettings>()
        .settings.enemySettings.factorySettings
    private val pool = ScorePointsPool(enemyFactorySettings.maxInstances)

    init {
        getRequiredService<GameObserver>().apply {
            addListener(this@ScorePointsFactory)
        }
    }

    override fun onEvent(event: IEvent) {
        if (event is GameEvents.EnemyDead) {
            pool.getInstance()?.apply {
                show(
                    event.enemy.transform.position,
                    event.enemy.points
                )
            }
        }
    }
}
