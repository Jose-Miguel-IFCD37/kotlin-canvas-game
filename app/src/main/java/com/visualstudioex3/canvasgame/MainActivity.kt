package com.visualstudioex3.canvasgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.visualstudioex3.canvasgame.engine.GameEngine
import com.visualstudioex3.canvasgame.game.Player
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
        var engine: GameEngine? by remember { mutableStateOf(null) }
        (engine ?: GameEngine.initialize()).GameLoop()
    }
}
