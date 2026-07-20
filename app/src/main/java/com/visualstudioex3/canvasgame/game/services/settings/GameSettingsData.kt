package com.visualstudioex3.canvasgame.game.services.settings

data class GameSettingsData(
    val playerSettings: PlayerSettingsData,
    val enemySettings: EnemySettingsData,
    val explossionSettings: ExplossionSettingsData,
)

data class FactorySettingsData(
    val maxInstances: Int,
    val spawnTime: Float = 0f
)

data class PlayerSettingsData(
    val lives: Int,
    val speed: Float,
    val bitmapResourceId: Int,
    val bulletsSettings: BulletSettingsData
)

data class BulletSettingsData(
    val speed: Float,
    val bitmapResourceId: Int,
    val factorySettings: FactorySettingsData
)

data class EnemySettingsData(
    val speed: Float,
    val factorySettings: FactorySettingsData,
    val asteroidSettings: AsteroidSettingsData,
    val enemyShipSettings: EnemyShipSettingsData
)

data class AsteroidSettingsData(
    val bitmapResourceIds: List<Int>,
    val rotation: Float
)

data class EnemyShipSettingsData(
    val bitmapResourceIds: List<Int>,
    val bulletsSettings: BulletSettingsData
)

data class ExplossionSettingsData(
    val maxRadius: Float,
    val growSpeed: Float,
    val factorySettings: FactorySettingsData
)
