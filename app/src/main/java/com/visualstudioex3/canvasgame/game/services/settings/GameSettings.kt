package com.visualstudioex3.canvasgame.game.services.settings

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.graphics.commands.TextAlign
import com.visualstudioex3.canvasgame.engine.scenes.IService

class GameSettings: IService {
    val settings = GameSettingsData(
        debugSettings = DebugSettingsData(
            showFPSCounter = true,
            showColliders = false,
        ),
        playerSettings = PlayerSettingsData(
            speed = 3f,
            bitmapResourceId = R.drawable.player_ship,
            bulletsSettings = BulletSettingsData(
                speed = 15f,
                bitmapResourceId = R.drawable.player_bullet,
                factorySettings = FactorySettingsData(
                    maxInstances = 5,
                    spawnTime = 0.75f
                )
            ),
            invulnerabilitySettings = PlayerInvulnerabilitySettingsData(
                duration = 5f,
                blinkInterval = 0.15f
            ),
            playerManagerSettings = PlayerManagerSettingsData(
                lives = 3,
                respawnDelay = 1.5f
            )
        ),
        enemySettings = EnemySettingsData(
            speed = 3f,
            factorySettings = FactorySettingsData(
                maxInstances = 5,
                spawnTime = 1.5f
            ),
            asteroidSettings = AsteroidSettingsData(
                bitmapResourceIds = listOf(
                    R.drawable.asteroid_1,
                    R.drawable.asteroid_2,
                    R.drawable.asteroid_3,
                    R.drawable.asteroid_4
                ),
                rotation = 45f,
                points = 250
            ),
            enemyShipSettings = EnemyShipSettingsData(
                bitmapResourceIds = listOf(
                    R.drawable.enemy_ship_1,
                    R.drawable.enemy_ship_2,
                    R.drawable.enemy_ship_3
                ),
                bulletsSettings = BulletSettingsData(
                    speed = 7.5f,
                    bitmapResourceId = R.drawable.enemy_bullet,
                    factorySettings = FactorySettingsData(
                        maxInstances = 3,
                        spawnTime = 2f
                    )
                ),
                points = 500
            )
        ),
        explossionSettings = ExplossionSettingsData(
            startRadius = 0.25f,
            endRadius = 2.5f,
            startColor = Color.WHITE.toColor(),
            endColor = Color.BLACK.toColor(),
            speed = 15f,
            factorySettings = FactorySettingsData(
                maxInstances = 5
            )
        ),
        scorerSettings = ScorerSettingsData(
            fontSize = 128f,
            align = TextAlign.Center,
            color = Color.WHITE.toColor(),
            format = "%08d",
        )
    )
}
