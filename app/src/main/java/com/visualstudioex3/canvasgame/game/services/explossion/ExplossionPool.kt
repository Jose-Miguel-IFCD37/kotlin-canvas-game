package com.visualstudioex3.canvasgame.game.services.explossion

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.Explossion

class ExplossionPool(
    instances: Int
): ObjectPool<Explossion>(instances) {
    override fun onCreateInstance(): Explossion =
        Explossion().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
