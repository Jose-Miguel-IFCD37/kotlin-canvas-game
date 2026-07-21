package com.visualstudioex3.canvasgame.game.entities.gamescene

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.graphics.components.CircleRenderer
import com.visualstudioex3.canvasgame.engine.graphics.utils.ColorUtils
import com.visualstudioex3.canvasgame.game.services.settings.ExplossionSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class Explossion: GameObject(), IEnableState {
    private val settings: ExplossionSettingsData = getRequiredService<GameSettings>()
        .settings.explossionSettings
    private val circle = addComponent<CircleRenderer>()

    override fun onEnable() {
        super.onEnable()
        circle.apply {
            radius = settings.startRadius
            color = settings.startColor
        }

    }

    override fun onUpdate(deltaTime: Float) {
        radiusStep(deltaTime)
        colorStep(deltaTime)

        if (isDone())
            enable = false
    }

    private fun radiusStep(deltaTime: Float) {
        circle.radius = MathF.lerp(
            circle.radius,
            settings.endRadius,
            settings.speed * deltaTime
        )
    }

    private fun colorStep(deltaTime: Float) {
        circle.color = ColorUtils.lerp(
            circle.color,
            settings.endColor,
            settings.speed * deltaTime
        )
    }

    private fun isDone(): Boolean =
        circle.radius >= settings.endRadius &&
        circle.color.toArgb() >= settings.endColor.toArgb()
}
