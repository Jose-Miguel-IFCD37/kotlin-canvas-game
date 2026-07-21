package com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.services

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.IService
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.systems.ExplossionPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ExplossionFactory: IService {
    private val settings: FactorySettingsData =
        SceneManager.scene.getRequiredService<GameSettings>()
        .settings.explossionSettings.factorySettings
    private val pool = ExplossionPool(settings.maxInstances)

    fun explode(position: PointF) {
        pool.getInstance().apply {
            this?.transform?.position = position
        }
    }
}
