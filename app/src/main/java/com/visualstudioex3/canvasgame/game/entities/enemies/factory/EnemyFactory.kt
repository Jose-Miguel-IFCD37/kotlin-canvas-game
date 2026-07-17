package com.visualstudioex3.canvasgame.game.entities.enemies.factory

class EnemyFactory {
    companion object {
        private const val MAX_INSTANCES: Int = 5
    }

    private val instances = EnemyPool(MAX_INSTANCES)

    fun spawnEnemy() {
        instances.getInstance()?.enable = true
    }
}
