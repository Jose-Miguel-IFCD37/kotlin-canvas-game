package com.visualstudioex3.canvasgame.engine.scenes

import com.visualstudioex3.canvasgame.engine.GameObject

abstract class Scene {
    val gameObjects = mutableListOf<GameObject>()
    val services = HashSet<IService>()

    inline fun <reified T> getService(): T?
            where T : IService =
        gameObjects.firstOrNull {
            it::class.java == T::class.java
        } as? T

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
