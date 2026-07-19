package com.visualstudioex3.canvasgame.game.entities.player

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager

class PlayerBulletPool(
    instances: Int
): ObjectPool<PlayerBullet>(instances) {
    override fun onCreateInstance(): PlayerBullet =
        PlayerBullet().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
