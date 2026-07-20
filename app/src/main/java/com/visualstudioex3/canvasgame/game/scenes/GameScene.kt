package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.game.entities.enemies.factory.EnemySpawner
import com.visualstudioex3.canvasgame.game.entities.player.Player
import com.visualstudioex3.canvasgame.game.entities.player.PlayerManager
import com.visualstudioex3.canvasgame.game.entities.scorer.GameScore
import com.visualstudioex3.canvasgame.game.services.explossion.ExplossionFactory
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class GameScene: Scene() {
    override fun onCreate() {
        services.add(GameSettings())
        services.add(ExplossionFactory())

        gameObjects.add(GameScore())
        gameObjects.add(Player())
        gameObjects.add(PlayerManager())
        gameObjects.add(EnemySpawner())
    }

    override fun onDestroy() {
    }
}
