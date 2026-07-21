package com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.services

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.EnemyBullet

class EnemyBulletPool(
    instances: Int
): ObjectPool<EnemyBullet>(instances) {
    override fun onCreateInstance(): EnemyBullet =
        EnemyBullet().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
