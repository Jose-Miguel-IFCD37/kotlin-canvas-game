package com.visualstudioex3.canvasgame.game.entities.enemies.factory

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Time

class EnemySpawner: GameObject() {
    companion object {
        private const val MAX_INSTANCES: Int = 5
        private const val TIME_SPAWN_NEW_ENEMY: Float = 3f
    }

    private val instances = EnemyPool(MAX_INSTANCES)
    private var time: Float = Time.getTime()

    override fun onUpdate(deltaTime: Float) {
        val now: Float = Time.getTime()
        if (now - time >= TIME_SPAWN_NEW_ENEMY) {
            instances.getInstance()
            time = now
        }
    }
}
