package com.visualstudioex3.canvasgame.game.entities.gameoverscene

import com.visualstudioex3.canvasgame.engine.GameEngine
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.input.components.InputTouch
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.scenes.GameScene

class GameOverInputListener: GameObject() {
    init {
        addComponent<InputTouch>().apply {
            onTap = {
                SceneManager.loadScene(GameScene())
            }
            onLongPress = {
                GameEngine.quit()
            }
        }
    }

    override fun onUpdate(deltaTime: Float) {
    }
}
