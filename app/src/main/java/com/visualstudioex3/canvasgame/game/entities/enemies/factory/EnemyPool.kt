package com.visualstudioex3.canvasgame.game.entities.enemies.factory

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.enemies.Asteroid
import com.visualstudioex3.canvasgame.game.entities.enemies.BaseEnemy
import com.visualstudioex3.canvasgame.game.entities.enemies.EnemyShip

class EnemyPool(
    instances: Int
): ObjectPool<BaseEnemy>(instances) {
    companion object {
        private val enemyTypes = listOf(
            Asteroid::class.java,
            EnemyShip::class.java
        )
    }

    override fun onCreateInstance(): BaseEnemy =
        (enemyTypes.random()
            .getDeclaredConstructor()
            .newInstance() as BaseEnemy).apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
