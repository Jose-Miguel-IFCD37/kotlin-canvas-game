package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerUILivesSettingsData

class PlayerUIIcon: GameObject() {
    val settings: PlayerUILivesSettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings.playerUILivesSettings
    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(settings.bitmapResourceId)
        }

        transform.translate(0.65f, 0.55f)
        transform.scale = 0.45f
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
