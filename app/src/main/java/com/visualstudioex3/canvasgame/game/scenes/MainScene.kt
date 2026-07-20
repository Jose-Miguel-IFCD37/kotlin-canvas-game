package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.game.entities.HelloWorld
import com.visualstudioex3.canvasgame.game.entities.player.Player
import com.visualstudioex3.canvasgame.game.entities.enemies.factory.EnemySpawner
import com.visualstudioex3.canvasgame.game.services.explossion.ExplossionFactory
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class MainScene: Scene() {
    override fun onCreate() {
        services.add(GameSettings())
        services.add(ExplossionFactory())

        gameObjects.add(HelloWorld())
        gameObjects.add(Player())
        gameObjects.add(EnemySpawner())

        RenderManager.showFPSCounter = true
    }

    override fun onDestroy() {
    }
}
