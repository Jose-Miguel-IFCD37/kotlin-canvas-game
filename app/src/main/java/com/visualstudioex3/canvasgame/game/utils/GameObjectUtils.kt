package com.visualstudioex3.canvasgame.game.utils

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteColliderRenderer
import com.visualstudioex3.canvasgame.game.services.settings.DebugSettingsData
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class GameObjectUtils {
    companion object {
        fun addSpriteColliderRendererIfDebugEnable(
            gameObject: GameObject
        ) {
            val settings: DebugSettingsData = gameObject.getService<GameSettings>()!!
                .settings.debugSettings

            if (settings.showColliders)
                gameObject.addComponent<SpriteColliderRenderer>()
        }
    }
}
