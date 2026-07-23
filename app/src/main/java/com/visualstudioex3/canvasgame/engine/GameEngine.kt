package com.visualstudioex3.canvasgame.engine

import android.content.Context
import android.graphics.PointF
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.visualstudioex3.canvasgame.engine.audio.AudioManager
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.input.InputManager
import com.visualstudioex3.canvasgame.engine.input.TouchGestures
import com.visualstudioex3.canvasgame.engine.physics.PhysicsManager
import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import kotlin.system.exitProcess

class GameEngine(
    activity: ComponentActivity,
    private val startScene: Scene
) {
    companion object {
        private lateinit var activity: ComponentActivity

        var gameActive: Boolean = false
            private set

        fun getContext(): Context = activity

        fun quit() {
            gameActive = false
        }
    }

    private lateinit var renderManager: RenderManager
    private val sceneManager = SceneManager()
    private val inputManager = InputManager()
    private val physicsManager = PhysicsManager()

    init {
        Companion.activity = activity
        hideSystemBars(activity)
        AudioManager.initialize()
    }

    @Composable
    fun GameLoop() {
        Surface(Modifier.fillMaxSize()) {
            val surfaceHolderCallback = remember {
                object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        gameLoop(
                            onStart = {
                                renderManager = RenderManager(holder)
                                SceneManager.loadScene(startScene)
                                gameActive = true
                            },
                            onQuit = {
                                SceneManager.scene.terminate()
                                GameResources.release()
                                quitApplication()
                            }
                        )
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
                                inputManager.cacheState(
                                    TouchGestures.Tap,
                                    PointF(offset.x, offset.y)
                                )
                            },
                            onDoubleTap = { offset ->
                                inputManager.cacheState(
                                    TouchGestures.DoubleTap,
                                    PointF(offset.x, offset.y)
                                )
                            },
                            onLongPress = { offset ->
                                inputManager.cacheState(
                                    TouchGestures.LongPress,
                                    PointF(offset.x, offset.y)
                                )
                            }
                        )
                    }
            )
        }
    }

    private fun hideSystemBars(activity: ComponentActivity) {
        activity.apply {
            val windowInsetsController: WindowInsetsControllerCompat =
                WindowCompat.getInsetsController(window, window.decorView)

            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_DEFAULT

            ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
                windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
                ViewCompat.onApplyWindowInsets(view, windowInsets)
            }
        }
    }

    private fun gameLoop(
        onStart: () -> Unit,
        onQuit: () -> Unit
    ) {
        Thread {
            onStart()

            while (gameActive) {
                val deltaTime: Float = Time.getDeltaTime()

                inputManager.update()
                sceneManager.update(deltaTime)
                physicsManager.update()
                renderManager.present()
            }

            onQuit()
        }.start()
    }

    private fun quitApplication() {
        exitProcess(0)
    }
}
