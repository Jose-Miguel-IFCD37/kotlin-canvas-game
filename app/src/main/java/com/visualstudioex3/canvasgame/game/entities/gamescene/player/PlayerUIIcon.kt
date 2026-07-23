package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.GameUISettingsData
import com.visualstudioex3.canvasgame.game.services.settings.PlayerUILivesSettingsData

class PlayerUIIcon: GameObject() {
    private val settings: GameUISettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings
    private val playerUILivesSettings: PlayerUILivesSettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings.playerUILivesSettings

    init {
        addComponent<SpriteRenderer>().apply {
            image = GameResources.loadBitmap(playerUILivesSettings.bitmapResourceId)
        }

        transform.translate(0.65f, 0.55f)
        transform.zOrder = settings.zOrder
        transform.scale = 0.45f
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
