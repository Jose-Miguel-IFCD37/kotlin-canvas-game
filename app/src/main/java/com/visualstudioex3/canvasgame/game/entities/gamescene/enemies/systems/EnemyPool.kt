package com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.systems

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.Asteroid
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.BaseEnemy
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.EnemyShip

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
        createInstanceOf(enemyTypes.random()).apply {
            SceneManager.scene.gameObjects.add(this)
        }

    private fun createInstanceOf(type: Class<out BaseEnemy>): BaseEnemy =
        type.getDeclaredConstructor()
            .newInstance() as BaseEnemy
}
