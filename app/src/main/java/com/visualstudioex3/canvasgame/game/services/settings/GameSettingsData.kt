package com.visualstudioex3.canvasgame.game.services.settings

import android.graphics.Color
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextAlign

data class GameSettingsData(
    val debugSettings: DebugSettingsData,
    val homeSettings: HomeSettingsData,
    val playerSettings: PlayerSettingsData,
    val enemySettings: EnemySettingsData,
    val explossionSettings: ExplossionSettingsData,
    val gameOverSettings: GameOverSettingsData,
    val gameUISettings: GameUISettingsData,
)

data class TextSettingsData(
    val stringResourceId: Int = 0,
    val fontSize: Float,
    val align: TextAlign,
    val color: Color,
    val format: String? = null
)

data class FactorySettingsData(
    val maxInstances: Int,
    val spawnTime: Float = 0f,
)

data class DebugSettingsData(
    val showFPSCounter: Boolean,
    val showColliders: Boolean,
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

data class HomeSettingsData(
    val title: TextSettingsData,
    val message: TextSettingsData,
    val bitmapResourceId: Int,
    val animationSpeed: Float,
    val fadeToBlackSpeed: Float,
)

data class GameOverSettingsData(
    val title: TextSettingsData,
    val message: TextSettingsData,
    val fadeToBlackSpeed: Float,
)

data class GameUISettingsData(
    val zOrder: Int,
    val scorerSettings: ScorerSettingsData,
    val playerUILivesSettings: PlayerUILivesSettingsData,
)

data class ScorerSettingsData(
    val mainScorerSettings: MainScorerSettingsData,
    val scorePointsSettings: ScorePointsSettingsData,
)

data class MainScorerSettingsData(
    val textSettings: TextSettingsData,
)

data class ScorePointsSettingsData(
    val textSettings: TextSettingsData,
    val fadeOutSpeed: Float,
)

data class PlayerUILivesSettingsData(
    val bitmapResourceId: Int,
    val textSettings: TextSettingsData,
)
