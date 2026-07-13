package com.visualstudioex3.canvasgame.engine

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import com.visualstudioex3.canvasgame.game.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class GameEngineSettings(
    val appResources: Resources,
    val materialTextStyle: TextStyle,
    val textMeasurer: TextMeasurer
)

class GameEngine {
    companion object {
        lateinit var globalSettings: GameEngineSettings
            private set

        @Composable
        fun initialize(): GameEngine {
            globalSettings = GameEngineSettings(
                LocalResources.current,
                materialTextStyle = LocalTextStyle.current,
                textMeasurer = TextMeasurer(
                    defaultFontFamilyResolver = LocalFontFamilyResolver.current,
                    defaultDensity = LocalDensity.current,
                    defaultLayoutDirection = LocalLayoutDirection.current
                )
            )

            return GameEngine()
        }
    }

    val gameObjects = mutableListOf<GameObject>()
    val player = Player()

    init {
        gameObjects.add(player)
        Log.e("FOO", "GameObjects: ${gameObjects.count()}")
    }

    @Composable
    fun GameLoop() {
        coroutineScope {
            while(true) {
                Log.d("game_loop", "Game loop started.")
                update()
                Draw()
                // TODO: Calculate wait for max FPS rate.
                Log.d("game_loop", "Game loop completed.")
            }
        }

    }

    private fun update() {
        gameObjects.forEach {
            if (it.enabled)
                it.update()
        }
    }

    @Composable
    private fun Draw() {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Color.Black)

            gameObjects.forEach {
                if (it.enabled)
                    it.apply {
                        draw()
                    }
            }
        }
    }
}
