package com.visualstudioex3.canvasgame.game.entities.gamescene.player.services

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.PlayerBullet

class PlayerBulletPool(
    instances: Int
): ObjectPool<PlayerBullet>(instances) {
    override fun onCreateInstance(): PlayerBullet =
        PlayerBullet().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
