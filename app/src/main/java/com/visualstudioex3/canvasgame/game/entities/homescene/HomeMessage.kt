package com.visualstudioex3.canvasgame.game.entities.homescene

import android.graphics.PointF
import androidx.core.graphics.plus
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.extensions.RectFExtensions.getCenter
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.TextSettingsData

class HomeMessage: GameObject() {
    val settings: TextSettingsData = getRequiredService<GameSettings>()
        .settings.homeSettings.message

    init {
        addComponent<TextRenderer>().apply {
            text = GameResources.getString(settings.stringResourceId)
            fontSize = settings.fontSize
            align = settings.align
            color = settings.color
            maxWidth = RenderManager.camera.width - 1f
        }

        transform.position = RenderManager.camera.getBounds().getCenter() +
                PointF(0f, 5f)
    }

    override fun onUpdate(deltaTime: Float) {

    }
}
