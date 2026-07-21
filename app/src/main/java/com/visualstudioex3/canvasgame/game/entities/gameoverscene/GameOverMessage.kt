package com.visualstudioex3.canvasgame.game.entities.gameoverscene

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.extensions.RectFExtensions.Companion.getCenter
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.TextSettingsData

class GameOverMessage: GameObject() {
    val settings: TextSettingsData = getService<GameSettings>()!!
        .settings.gameOverSettings.message

    init {
        addComponent<TextRenderer>().apply {
            text = GameResources.getString(settings.stringResourceId)
            fontSize = settings.fontSize
            align = settings.align
            color = settings.color
            maxWidth = RenderManager.camera.width - 1f
        }

        transform.position = RenderManager.camera.getBounds().getCenter()
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
