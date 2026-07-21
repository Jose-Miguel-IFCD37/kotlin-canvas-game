package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.GameOverMessage
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.GameOverTitle
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class GameOverScene: Scene() {
    override fun onCreate() {
        services.add(GameSettings())

        gameObjects.add(GameOverTitle())
        gameObjects.add(GameOverMessage())
    }

    override fun onDestroy() {
    }
}
