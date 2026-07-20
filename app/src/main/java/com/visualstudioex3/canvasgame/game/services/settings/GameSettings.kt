package com.visualstudioex3.canvasgame.game.services.settings

import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.scenes.IService

class GameSettings: IService {
    val settings = GameSettingsData(
        playerSettings = PlayerSettingsData(
            lives = 3,
            speed = 3f,
            bitmapResourceId = R.drawable.player_ship,
            bulletsSettings = BulletSettingsData(
                speed = 15f,
                bitmapResourceId = R.drawable.player_bullet,
                factorySettings = FactorySettingsData(
                    maxInstances = 5,
                    spawnTime = 0.25f
                )
            )
        ),
        enemySettings = EnemySettingsData(
            speed = 3f,
            factorySettings = FactorySettingsData(
                maxInstances = 5,
                spawnTime = 3f
            ),
            asteroidSettings = AsteroidSettingsData(
                bitmapResourceIds = listOf(
                    R.drawable.asteroid_1,
                    R.drawable.asteroid_2,
                    R.drawable.asteroid_3,
                    R.drawable.asteroid_4
                ),
                rotation = 45f
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
                        spawnTime = 2.5f
                    )
                )
            )
        ),
        explossionSettings = ExplossionSettingsData(
            maxRadius = 25f,
            growSpeed = 50f,
            factorySettings = FactorySettingsData(
                maxInstances = 5
            )
        )
    )
}
