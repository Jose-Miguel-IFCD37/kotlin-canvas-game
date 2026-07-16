package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.view.SurfaceHolder
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.IDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextAlign
import com.visualstudioex3.canvasgame.engine.graphics.drawcommands.TextDrawCommand
import com.visualstudioex3.canvasgame.engine.graphics.drawprocessors.DrawProcessorFactory

/*
    En videojuegos y multimedia en general el renderizado se implementa mediante una arquitectura de
    comandos. Los comandos simplemente definen los parametros de una operacion de dibujo:
     - Coordenadas de posicion
     - Rotacion
     - Escalado
     - Material (Color, textura, fuente de texto, geometria 3D...)

     Cada objeto del juego compone y envia estos comandos a una lista del renderizador. Este, cuando
     se le llama a presentar en pantalla el resultado, ejecuta secuencialmente toda la lista de
     comandos usando procesadores. Cada procesador se encarga de una operacion grafica concreta:
     dibujar texto, dibujar una imagen, dibujar un modelo 3D...
 */
class RenderManager(
    private val surfaceHolder: SurfaceHolder
) {
    companion object {
        private lateinit var _camera: Camera
        private val commands = ArrayDeque<IDrawCommand>()

        /*
            Generalmente los motores suelen permitir definir multiples camaras para realizar
            composiciones complejas o efectos visuales avanzados. En este motor simplemente usaremos
            una unica camara.
         */
        val camera: Camera
            get() = _camera
        val primitives = Primitives()

        var showFPSCounter: Boolean = false

        fun addDrawCommand(command: IDrawCommand) {
            commands.add(command)
        }
    }

    private val drawProcessors = DrawProcessorFactory()

    init {
        _camera = Camera(surfaceHolder)
    }

    fun present() {
        val canvas: Canvas = surfaceHolder.lockCanvas()

        if (showFPSCounter)
            addFPSCounterCommand()

        synchronized(surfaceHolder) {
            primitives.setCanvas(canvas)

            canvas.drawColor(Color.BLACK)

            while (commands.isNotEmpty())
                drawProcessors.process(canvas, commands.removeFirst())
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    private fun addFPSCounterCommand() {
        addDrawCommand(
            TextDrawCommand(
                position = PointF(camera.width - 1f, 0.5f),
                color = Color.YELLOW.toColor(),
                fontSize = 48f,
                align = TextAlign.Right,
                text = "%,.2ffps".format(Time.framesPerSecond)
            )
        )
    }
}
