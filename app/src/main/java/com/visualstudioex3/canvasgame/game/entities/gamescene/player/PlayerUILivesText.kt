package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.GameUISettingsData
import com.visualstudioex3.canvasgame.game.services.settings.TextSettingsData

class PlayerUILivesText : GameObject(), IEventListener {
    private val settings: GameUISettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings
    private val playerUILivesTextSettings: TextSettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings.playerUILivesSettings.textSettings
    private val textRenderer = addComponent<TextRenderer>().apply {
        color = playerUILivesTextSettings.color
        fontSize = playerUILivesTextSettings.fontSize
        align = playerUILivesTextSettings.align
    }

    init {
        getRequiredService<GameObserver>().apply {
            addListener(this@PlayerUILivesText)
        }
        transform.translate(1.25f)
        transform.zOrder = settings.zOrder
    }

    override fun onUpdate(deltaTime: Float) {
    }

    override fun onEvent(event: IEvent) {
        if (event is GameEvents.PlayerLivesCountChanged) {
            updateLivesCounter(event.lives)
        }
    }

    private fun updateLivesCounter(lives: Int) {
        textRenderer.text = playerUILivesTextSettings.format!!
            .format(lives - 1)
    }
}
