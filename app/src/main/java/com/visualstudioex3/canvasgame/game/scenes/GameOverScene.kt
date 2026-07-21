package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.FadeToBlackBackground
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.GameOverInputListener
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.GameOverMessage
import com.visualstudioex3.canvasgame.game.entities.gameoverscene.GameOverTitle
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class GameOverScene: Scene() {
    override fun onCreate() {
        addService<GameSettings>()

        addGameObject<GameOverTitle>()
        addGameObject<GameOverMessage>()
        addGameObject<GameOverInputListener>()
        addGameObject<FadeToBlackBackground>()
    }

    override fun onDestroy() {
    }
}
