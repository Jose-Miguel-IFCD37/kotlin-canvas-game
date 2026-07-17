package com.visualstudioex3.canvasgame.engine.scenes

import com.visualstudioex3.canvasgame.engine.GameObject

/*
    Las escenas habitualmente gestionan un grafo o jerarquita de objetos. En nuestro caso se
    implementa como una lista plana de objetos.
 */
abstract class Scene {
    companion object {
        val gameObjects = mutableListOf<GameObject>()
    }

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
