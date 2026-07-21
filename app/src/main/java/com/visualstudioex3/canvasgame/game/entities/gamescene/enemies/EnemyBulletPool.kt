package com.visualstudioex3.canvasgame.game.entities.gamescene.enemies

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager

class EnemyBulletPool(
    instances: Int
): ObjectPool<EnemyBullet>(instances) {
    override fun onCreateInstance(): EnemyBullet =
        EnemyBullet().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
