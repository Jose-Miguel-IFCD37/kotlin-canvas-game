package com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.services

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameService
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.systems.ExplossionPool
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ExplossionFactory: GameService(), IEventListener {
    private val settings: FactorySettingsData = getRequiredService<GameSettings>()
        .settings.explossionSettings.factorySettings
    private val pool = ExplossionPool(settings.maxInstances)
    private val gameObserver = getRequiredService<GameObserver>().apply {
        addListener(this@ExplossionFactory)
    }

    override fun onEvent(event: IEvent) {
        when(event) {
            is GameEvents.PlayerDead -> explode(event.player.transform.position)
            is GameEvents.EnemyDead -> explode(event.enemy.transform.position)
        }
    }

    private fun explode(position: PointF) {
        pool.getInstance()?.apply {
            transform.position = position
        }
    }
}
