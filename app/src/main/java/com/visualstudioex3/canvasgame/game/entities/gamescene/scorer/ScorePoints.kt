package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer

import android.graphics.Color
import android.graphics.PointF
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.graphics.components.FadeColor
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.GameUISettingsData
import com.visualstudioex3.canvasgame.game.services.settings.ScorePointsSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.TextSettingsData

class ScorePoints : GameObject(), IEnableState {
    private val settings: GameUISettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings
    private val socrePointsSettings: ScorePointsSettingsData =
        settings.scorerSettings.scorePointsSettings
    private val socrePointsTextSettings: TextSettingsData =
        settings.scorerSettings.scorePointsSettings.textSettings
    private val textRenderer = addComponent<TextRenderer>().apply {
        fontSize = socrePointsTextSettings.fontSize
        align = socrePointsTextSettings.align
    }
    private val fadeColor = addComponent<FadeColor>().apply {
        startColor = socrePointsTextSettings.color
        stopColor = Color.TRANSPARENT.toColor()
        speed = socrePointsSettings.fadeOutSpeed
        onComplete = {
            this@ScorePoints.enable = false
        }
    }

    init {
        transform.zOrder = settings.zOrder
    }

    override fun onUpdate(deltaTime: Float) {
        textRenderer.color = fadeColor.currentColor
        transform.move(y = -1f * deltaTime)
    }

    fun show(position: PointF, points: Int) {
        transform.position = position
        textRenderer.text = socrePointsTextSettings.format!!
            .format(points)
        fadeColor.reset()
        enable = true
    }
}
