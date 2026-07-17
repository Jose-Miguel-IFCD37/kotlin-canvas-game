package com.visualstudioex3.canvasgame.engine

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
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager
import com.visualstudioex3.canvasgame.engine.input.InputManager
import com.visualstudioex3.canvasgame.engine.input.TouchGestures
import com.visualstudioex3.canvasgame.engine.scenes.Scene
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import kotlin.system.exitProcess

class GameEngine(
    activity: ComponentActivity,
    private val startScene: Scene
) {
    companion object {
        var gameActive: Boolean = false
            private set

        fun quit() {
            gameActive = false
        }
    }

    private lateinit var renderManager: RenderManager
    private val sceneManager = SceneManager()
    private val inputManager = InputManager()

    init {
        hideSystemBars(activity)
        GameResources.setResources(activity.resources)
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

    // Ocultamos las barras de sistema de Android para tener toda la vista disponible:
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

    /*
    * Desde hace muchos años (y con muchos años estoy hablando de mediados de la decada de los 2000)
    * se considera muy mala practica bloquear el bucle principal para realizar la espera fija de FPS.
    *
    * Desde que los procesadores de escritorio y consolas soportan programacion multihilo, la buena
    * practica es dejar libre el bucle para que se ejecute lo más rapido posible. Esto de base
    * permite aprovechar mejor el rendimeinto sobre todo a nivel de GPU y lograr mejorar rendimiento
    * en maquinas con poca potencia.
    *
    * Para lograr una tasa de refresco de FPS estable lo que se recomienda siempre es activar la
    * espera de refresco vertical del monitor (VSync). Esto forzara a que el bucle del juego
    * se ejecute como maximo a la misma frecuencia que el monitor (e.g.: 50hz = 50fps, 60hz = 60fps,
    * 120hz = 120fps). Existe tambien la opcion de doble espera (doble VSync) para forzar tasa de
    * refresco a 30fps (esto se usa mucho en Unity cuando el juego no alcanza una tasa de refresco
    * estable por encima de 30-40fps en un sistema como Nintendo Switch o PlayStation 4).
    *
    * XNA/MonoGame, Unity o Unreal Engine funcionan de esta manera.
    *
    * Luego, tanto en el caso de tener una tasa de refresco variable o fija, para garantizar que la
    * logica del juego (movimientos, animaciones, etc...) vayan siempre a una misma velocidad y
    * fluida, lo que se suele hacer es calcular el tiempo de ejecucion que ha tardado el ultimo
    * ciclo del bucle (delta time) y aplicarlo en toda logica que se vea afectada por la
    * velocidad de refresco de la pantalla.
    *
    * Sobre consumo de recursos y CPU, el bucle del juego va a consumir exactamente lo mismo si
    * realiza una espera como si no. Ni va a consumir más RAM ni va a ocupar más recursos de la CPU.
    *
    * ---
    *
    * En este pequeño motor el bucle funciona libre y se calcula el delta time para pasarlo como
    * parametro en las llamadas onUpdate de cada gameObject. Dado que estamos usando Canvas y la
    * propia API grafica de la interfaz de usuario de Android en vez de una API grafica a bajo nivel
    * como OpenGL o Vulkan, la espera de refresco vertical (VSync) esta implicita, por lo que la
    * tasa de refresco estara limitada a la potencia de la pantalla (en mis pruebas desde el
    * emulador, la tasa de FPS es más o menos constante a 60fps, 55~70fps, percibiendo fluidez
    * suficiente junto a la aplicacion de delta time en la logica).
    */
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
                renderManager.present()
            }

            onQuit()
        }.start()
    }

    private fun quitApplication() {
        exitProcess(0)
    }
}
