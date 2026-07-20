package com.visualstudioex3.canvasgame.engine.scenes

class SceneManager {
    companion object {
        private var currentScene: Scene? = null
        private var nextScene: Scene? = null

        val scene: Scene
            get() = currentScene ?: error("No hay escena activa.")

        fun loadScene(scene: Scene) {
            nextScene = scene
        }
    }

    fun update(deltaTime: Float) {
        currentScene?.onFrame(deltaTime)

        if (nextScene != null)
            switchToNextScene()
    }

    private fun switchToNextScene() {
        currentScene?.onDestroy()
        currentScene = nextScene
        currentScene?.initialize()
        nextScene = null
    }
}
