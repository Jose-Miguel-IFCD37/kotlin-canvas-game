package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.graphics.components.TextRenderer
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.GameUISettingsData
import com.visualstudioex3.canvasgame.game.services.settings.TextSettingsData

class GameScore : GameObject(), IEventListener {
    private val settings: GameUISettingsData = getRequiredService<GameSettings>()
        .settings.gameUISettings
    private val mainScorerTextSettings: TextSettingsData =
        settings.scorerSettings.mainScorerSettings.textSettings
    private val textRenderer = addComponent<TextRenderer>().apply {
        fontSize = mainScorerTextSettings.fontSize
        color = mainScorerTextSettings.color
        align = mainScorerTextSettings.align
    }

    init {
        getRequiredService<GameObserver>().apply {
            addListener(this@GameScore)
        }
    }

    var score: Int = 0
        private set(value) {
            field = value
            textRenderer.text = mainScorerTextSettings.format!!
                .format(score)
        }

    init {
        transform.translate(
            x = RenderManager.camera.width - 0.1f
        )
        transform.zOrder = settings.zOrder
        score = 0
    }

    override fun onUpdate(deltaTime: Float) {
    }

    override fun onEvent(event: IEvent) {
        if (event is GameEvents.EnemyDead) {
            score += event.enemy.points
        }
    }
}
