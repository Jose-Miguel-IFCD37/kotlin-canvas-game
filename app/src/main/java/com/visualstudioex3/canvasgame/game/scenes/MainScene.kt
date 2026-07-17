package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.game.entities.HelloWorld
import com.visualstudioex3.canvasgame.game.entities.Player

class MainScene: Scene() {
    override fun onCreate() {
        gameObjects.add(HelloWorld())
        gameObjects.add(Player())

        RenderManager.showFPSCounter = true
    }

    override fun onDestroy() {
    }
}
