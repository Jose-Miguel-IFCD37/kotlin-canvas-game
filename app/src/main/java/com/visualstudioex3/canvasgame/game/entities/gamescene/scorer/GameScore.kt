package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.ScorerSettingsData

class GameScore: GameObject() {
    private val settings: ScorerSettingsData = getService<GameSettings>()!!
        .settings.scorerSettings
    private val textRenderer = addComponent<TextRenderer>().apply {
        fontSize = settings.fontSize
        color = settings.color
        align = settings.align
    }

    var score: Int = 0
        private set(value) {
            field = value

            textRenderer.text = settings.format.format(score)
        }

    init {
        transform.position = PointF(
            RenderManager.camera.width / 2f,
            1.25f
        )

        score = 0
    }

    fun addPoints(points: Int) {
        score += points
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
