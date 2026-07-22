package com.visualstudioex3.canvasgame.engine

import com.visualstudioex3.canvasgame.engine.scenes.SceneManager

abstract class GameService {
    inline fun <reified T> getService(): T?
            where T : GameService =
        SceneManager.scene.getService()

    inline fun <reified T> getRequiredService(): T
            where T : GameService =
        SceneManager.scene.getRequiredService()
}
