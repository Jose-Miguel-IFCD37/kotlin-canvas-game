package com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.services

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameService
import com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.systems.ExplossionPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ExplossionFactory: GameService() {
    private val settings: FactorySettingsData = getRequiredService<GameSettings>()
        .settings.explossionSettings.factorySettings
    private val pool = ExplossionPool(settings.maxInstances)

    fun explode(position: PointF) {
        pool.getInstance()?.apply {
            transform.position = position
        }
    }
}
