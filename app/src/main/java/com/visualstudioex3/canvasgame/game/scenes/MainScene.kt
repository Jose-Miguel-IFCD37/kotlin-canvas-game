package com.visualstudioex3.canvasgame.game.scenes

import com.visualstudioex3.canvasgame.engine.Scene
import com.visualstudioex3.canvasgame.game.entities.HelloWorld

class MainScene: Scene() {
    override fun onCreate() {
        gameObjects.add(HelloWorld())
    }

    override fun onDestroy() {
    }
}
