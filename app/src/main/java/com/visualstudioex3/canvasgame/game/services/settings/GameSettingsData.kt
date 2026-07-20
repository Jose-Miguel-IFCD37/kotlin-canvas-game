package com.visualstudioex3.canvasgame.game.services.settings

import android.graphics.Color
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextAlign

data class GameSettingsData(
    val debugSettings: DebugSettingsData,
    val playerSettings: PlayerSettingsData,
    val enemySettings: EnemySettingsData,
    val explossionSettings: ExplossionSettingsData,
    val scorerSettings: ScorerSettingsData,
)

data class DebugSettingsData(
    val showFPSCounter: Boolean,
    val showColliders: Boolean,
)

data class FactorySettingsData(
    val maxInstances: Int,
    val spawnTime: Float = 0f,
)

data class PlayerSettingsData(
    val speed: Float,
    val bitmapResourceId: Int,
    val bulletsSettings: BulletSettingsData,
    val invulnerabilitySettings: PlayerInvulnerabilitySettingsData,
    val playerManagerSettings: PlayerManagerSettingsData,
)

data class PlayerManagerSettingsData(
    val lives: Int,
    val respawnDelay: Float,
)

data class PlayerInvulnerabilitySettingsData(
    val duration: Float,
    val blinkInterval: Float,
)

data class BulletSettingsData(
    val speed: Float,
    val bitmapResourceId: Int,
    val factorySettings: FactorySettingsData,
)

data class EnemySettingsData(
    val speed: Float,
    val factorySettings: FactorySettingsData,
    val asteroidSettings: AsteroidSettingsData,
    val enemyShipSettings: EnemyShipSettingsData,
)

data class AsteroidSettingsData(
    val bitmapResourceIds: List<Int>,
    val rotation: Float,
    val points: Int,
)

data class EnemyShipSettingsData(
    val bitmapResourceIds: List<Int>,
    val bulletsSettings: BulletSettingsData,
    val points: Int,
)

data class ExplossionSettingsData(
    val startRadius: Float,
    val endRadius: Float,
    val startColor: Color,
    val endColor: Color,
    val speed: Float,
    val factorySettings: FactorySettingsData,
)

data class ScorerSettingsData(
    val fontSize: Float,
    val align: TextAlign,
    val color: Color,
    val format: String,
)
