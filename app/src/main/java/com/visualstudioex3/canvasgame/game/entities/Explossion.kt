package com.visualstudioex3.canvasgame.game.entities

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.engine.graphics.components.CircleRenderer
import com.visualstudioex3.canvasgame.game.services.settings.ExplossionSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class Explossion: GameObject(), IEnableState {
    private val settings: ExplossionSettingsData = getService<GameSettings>()!!
        .settings.explossionSettings
    private val circle = addComponent<CircleRenderer>()
    private val time: Float = Time.getTime()

    override fun onUpdate(deltaTime: Float) {
        MathF.lerp(
            circle.radius,
            settings.maxRadius,
            settings.growSpeed * deltaTime
        )

        if (circle.radius >= settings.maxRadius)
            enable = false
    }
}
