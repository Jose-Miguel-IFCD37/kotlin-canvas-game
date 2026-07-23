package com.visualstudioex3.canvasgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.visualstudioex3.canvasgame.engine.GameEngine
import com.visualstudioex3.canvasgame.game.scenes.HomeScene
import com.visualstudioex3.canvasgame.ui.theme.CanvasGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasGameTheme {
                Game()
            }
        }
    }

    @Composable
    fun Game() {
        val gameEngine: GameEngine = remember {
            GameEngine(this, HomeScene())
        }

        gameEngine.GameLoop()
    }
}
