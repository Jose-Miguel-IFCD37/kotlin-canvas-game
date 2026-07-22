package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerUILivesSettingsData

class PlayerUILivesText : GameObject(), IEventListener {
    val settings: PlayerUILivesSettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings.playerUILivesSettings
    val textRenderer = addComponent<TextRenderer>().apply {
        color = settings.textSettings.color
        fontSize = settings.textSettings.fontSize
        align = settings.textSettings.align
    }

    init {
        getRequiredService<GameObserver>().apply {
            addListener(this@PlayerUILivesText)
        }
        transform.translate(1.25f)
    }

    override fun onUpdate(deltaTime: Float) {
    }

    override fun onEvent(event: IEvent) {
        if (event is GameEvents.PlayerLivesCountChanged) {
            updateLivesCounter(event.lives)
        }
    }

    private fun updateLivesCounter(lives: Int) {
        textRenderer.text = settings.textSettings.format!!
            .format(lives)
    }
}
