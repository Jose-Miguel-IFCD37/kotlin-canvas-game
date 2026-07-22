package com.visualstudioex3.canvasgame.game.services.events

import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.game.entities.gamescene.enemies.BaseEnemy
import com.visualstudioex3.canvasgame.game.entities.gamescene.player.Player

sealed class GameEvents: IEvent {
    data class PlayerDead(
        val player: Player
    ): GameEvents()

    data class PlayerLivesCountChanged(
        val lives: Int
    ): GameEvents()

    data class EnemyDead(
        val enemy: BaseEnemy
    ): GameEvents()
}
