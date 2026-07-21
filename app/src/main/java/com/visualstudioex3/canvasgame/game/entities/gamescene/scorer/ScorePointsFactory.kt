package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.IService
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.systems.ScorePointsPool
import com.visualstudioex3.canvasgame.game.services.settings.FactorySettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class ScorePointsFactory: IService {
    private val enemyFactorySettings: FactorySettingsData = SceneManager.scene.getRequiredService<GameSettings>()
        .settings.enemySettings.factorySettings
    private val pool = ScorePointsPool(enemyFactorySettings.maxInstances)

    fun show(position: PointF, points: Int) {
        pool.getInstance()?.apply {
            show(position, points)
        }
    }
}
