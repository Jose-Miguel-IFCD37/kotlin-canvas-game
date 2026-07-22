package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer

import android.graphics.Color
import android.graphics.PointF
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.graphics.components.FadeColor
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.ScorePointsSettingsData

class ScorePoints : GameObject(), IEnableState {
    private val settings: ScorePointsSettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings.scorerSettings.scorePointsSettings
    private val textRenderer = addComponent<TextRenderer>().apply {
        fontSize = settings.textSettings.fontSize
        align = settings.textSettings.align
    }
    private val fadeColor = addComponent<FadeColor>().apply {
        startColor = settings.textSettings.color
        stopColor = Color.TRANSPARENT.toColor()
        speed = settings.fadeOutSpeed
        onComplete = {
            this@ScorePoints.enable = false
        }
    }

    override fun onUpdate(deltaTime: Float) {
        textRenderer.color = fadeColor.currentColor
        transform.move(y = -1f * deltaTime)
    }

    fun show(position: PointF, points: Int) {
        transform.position = position
        textRenderer.text = settings.textSettings.format!!
            .format(points)
        fadeColor.reset()
        enable = true
    }
}
