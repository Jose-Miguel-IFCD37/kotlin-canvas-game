package com.visualstudioex3.canvasgame.game.entities.gamescene.player

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Timer
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.scenes.GameOverScene
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings
import com.visualstudioex3.canvasgame.game.services.settings.PlayerManagerSettingsData

class PlayerManager : GameObject() {
    private val settings: PlayerManagerSettingsData =
        SceneManager.scene.getService<GameSettings>()!!
            .settings.playerSettings.playerManagerSettings
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
    private var lives: Int = settings.lives

    override fun onUpdate(deltaTime: Float) {
        resolvePlayerIfNull()
        timer.update()
    }

    private fun resolvePlayerIfNull() {
        if (player == null) {
            player = findGameObject<Player>()
            player?.onPlayerDead = {
                timer.enable = true
            }
        }
    }
}
