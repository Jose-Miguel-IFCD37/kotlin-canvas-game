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

class HomeTitle: GameObject() {
    val settings: TextSettingsData = getRequiredService<GameSettings>()
        .settings.homeSettings.title

    init {
        addComponent<TextRenderer>().apply {
            text = GameResources.getString(settings.stringResourceId)
            fontSize = settings.fontSize
            align = settings.align
            color = settings.color
        }

        transform.position = RenderManager.camera.getBounds().getCenter() +
                PointF(0f, -7f)
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
