package com.visualstudioex3.canvasgame.game.entities.homescene

import android.util.Log
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.engine.extensions.RectFExtensions.getCenter
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.HomeSettingsData

class HomePlayerShip : GameObject() {
    val settings: HomeSettingsData = getRequiredService<GameSettings>()
        .settings.homeSettings

    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(settings.bitmapResourceId)
        }
        transform.position = RenderManager.camera.getBounds().getCenter()
    }

    override fun onUpdate(deltaTime: Float) {
        transform.scale = MathF.pingPong(
            Time.getTime() * settings.animationSpeed,
            1f
        ) + 1.5f
    }
}
