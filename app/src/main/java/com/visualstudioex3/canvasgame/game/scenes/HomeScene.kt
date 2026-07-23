package com.visualstudioex3.canvasgame.game.scenes

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.game.entities.FadeToBlackBackground
import com.visualstudioex3.canvasgame.game.entities.homescene.HomeInputListener
import com.visualstudioex3.canvasgame.game.entities.homescene.HomePlayerShip
import com.visualstudioex3.canvasgame.game.entities.homescene.HomeMessage
import com.visualstudioex3.canvasgame.game.entities.homescene.HomeTitle
import com.visualstudioex3.canvasgame.game.services.settings.GameSettings

class HomeScene: Scene() {
    override fun onCreate() {
        addService<GameSettings>().apply {
            RenderManager.showFPSCounter = settings.debugSettings.showFPSCounter
        }

        addGameObject<HomeTitle>()
        addGameObject<HomeMessage>()
        addGameObject<HomePlayerShip>()
        addGameObject<HomeInputListener>()
        addGameObject<FadeToBlackBackground>()

        RenderManager.clearColor = Color.BLACK.toColor()
    }

    override fun onDestroy() {
    }
}
