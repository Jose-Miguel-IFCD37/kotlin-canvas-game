package com.visualstudioex3.canvasgame.engine

abstract class Scene {
    companion object {
        val gameObjects = mutableListOf<GameObject>()
    }

    abstract fun onCreate()

    abstract fun onDestroy()

    fun update() {
        gameObjects.forEach {
            if (it.enabled)
                it.onUpdate()
        }
    }

    fun draw() {
        gameObjects.forEach {
            if (it.enabled)
                it.draw()
        }
    }
}
