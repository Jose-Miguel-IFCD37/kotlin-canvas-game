package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Timer
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.scenes.GameOverScene
import com.visualstudioex3.canvasgame.game.services.events.GameEvents
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerManagerSettingsData

class PlayerManager : GameObject(), IEventListener {
    private val settings: PlayerManagerSettingsData =
        SceneManager.scene.getRequiredService<GameSettings>()
            .settings.playerSettings.playerManagerSettings
    private val gameObserver = getRequiredService<GameObserver>().apply {
        addListener(this@PlayerManager)
    }
    private val timer = Timer().apply {
        enable = false
        interval = settings.respawnDelay
        onTime = {
            if (--lives == 0) {
                SceneManager.loadScene(GameOverScene())
            } else {
                player?.enable = true
                this.enable = false
            }
        }
    }

    private var player: Player? = null
    private var lives: Int = 0
        set(value) {
            field = value
            gameObserver.notifyEvent(
                GameEvents.PlayerLivesCountChanged(value)
            )
        }

    override fun onEnable() {
        lives = settings.lives
    }

    override fun onUpdate(deltaTime: Float) {
        resolvePlayerIfNull()
        timer.update()
    }

    private fun resolvePlayerIfNull() {
        if (player == null) {
            player = findGameObject<Player>()
        }
    }

    override fun onEvent(event: IEvent) {
        if (event is GameEvents.PlayerDead) {
            timer.enable = true
        }
    }
}
