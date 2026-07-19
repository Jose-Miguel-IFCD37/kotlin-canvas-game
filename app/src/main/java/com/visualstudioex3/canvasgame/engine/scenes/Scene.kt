package com.visualstudioex3.canvasgame.engine.scenes

import com.visualstudioex3.canvasgame.engine.GameObject

abstract class Scene {
    val gameObjects = mutableListOf<GameObject>()
    val services = mutableListOf<IService>()

    abstract fun onCreate()

    abstract fun onDestroy()

    fun onFrame(deltaTime: Float) {
        update(deltaTime)
        draw()
    }

    private fun update(deltaTime: Float) {
        gameObjects.forEach {
            if (it.enable)
                it.update(deltaTime)
        }
    }

    private fun draw() {
        gameObjects.forEach {
            if (it.enable)
                it.draw()
        }
    }
}
