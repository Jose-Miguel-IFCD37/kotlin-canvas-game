package com.visualstudioex3.canvasgame.game.entities.gameoverscene

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.FadeColor
import com.visualstudioex3.canvasgame.game.services.settings.GameOverSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class FadeToBlackBackground: GameObject() {
    val settings: GameOverSettingsData = getService<GameSettings>()!!
        .settings.gameOverSettings
    val fadeColor = addComponent<FadeColor>().apply {
        startColor = Color.WHITE.toColor()
        stopColor = Color.BLACK.toColor()
        speed = settings.fadeToBlackSpeed
    }

    override fun onUpdate(deltaTime: Float) {
        RenderManager.clearColor = fadeColor.currentColor
    }
}
