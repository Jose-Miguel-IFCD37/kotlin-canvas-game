package com.visualstudioex3.canvasgame.engine

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.viewinterop.AndroidView
import com.visualstudioex3.canvasgame.engine.graphics.GameRender
import java.lang.Thread.sleep
import kotlin.system.exitProcess

class GameEngine(
    startScene: Scene
) {
    companion object {
        private lateinit var _currentScene: Scene

        val scene: Scene
            get() = _currentScene

        var gameLoop = true

        fun loadScene(scene: Scene) {
            _currentScene.onDestroy()
            _currentScene = scene
            _currentScene.onCreate()
        }
    }

    init {
        _currentScene = startScene
        _currentScene.onCreate()
    }

    @Composable
    fun GameLoop() {
        Surface(Modifier.fillMaxSize()) {
            val surfaceHolderCallback = remember {
                object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        Thread {
                            val render = GameRender(holder)

                            while (gameLoop) {
                                _currentScene.update()
                                _currentScene.draw()

                                render.present()
                                // TODO: Implement proper wait until max FPS rate.
                                sleep(16)
                            }

                            quitApplication()
                        }.start()
                    }

                    override fun surfaceChanged(
                        holder: SurfaceHolder,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {
                    }

                    override fun surfaceDestroyed(holder: SurfaceHolder) {
                    }
                }
            }

            AndroidView(
                factory = { context ->
                    SurfaceView(context).apply {
                        holder.addCallback(surfaceHolderCallback)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                // TODO: Implement onTap callback.
                            },
                            onLongPress = { offset ->

                            }
                        )
                    }
            )
        }
    }

    private fun quitApplication() {
        exitProcess(0)
    }
}
