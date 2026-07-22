package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.EnemySpawner
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.Player
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.PlayerManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.GameScore
import com.visualstudioex3.canvasgame.game.entities.gamescene.explossion.services.ExplossionFactory
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.services.ScorePointsFactory
import com.visualstudioex3.canvasgame.game.services.events.GameObserver
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class GameScene: Scene() {
    override fun onCreate() {
        addService<GameSettings>()
        addService<GameObserver>()
        addService<ExplossionFactory>()
        addService<ScorePointsFactory>()

        addGameObject<GameScore>()
        addGameObject<Player>()
        addGameObject<PlayerManager>()
        addGameObject<EnemySpawner>()
    }

    override fun onDestroy() {
    }
}
