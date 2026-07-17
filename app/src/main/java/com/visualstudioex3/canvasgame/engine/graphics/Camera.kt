package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Point
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceHolder
import androidx.core.graphics.div
import androidx.core.graphics.times

/*
    Camara que implemente sistema de coordenadas virtual para abstraernos de las dimensiones reales
    de la pantalla del dispositivo.

    Igual que en Unity y otros motores, normalizamos el sistema a unidades en coma floante. En este
    caso el calculo y conversion se realiza con el factor de ratio de aspecto de la pantalla.

    Esto permite trabajar siempre con un sistema de coordenadas universal, (1.0f, 3.5f) vs (125, 370)
    y que funcione igual en cualquier dispositivo tenga la resolucion que tenga. Solo habria que
    tener en cuenta los posibles ratios de pantalla para posicion correctamente elementos de
    interfaz en pantalla y definir una area segura de juego que entre en cualquier pantalla (esto en
    general se consigue o bien obteniendo el "safe area" si la proporciona el hardware o bien
    definiendo una lista de areas seguras usando los ratios de pantalla estandar del mercado).
 */
class Camera(
    surfaceHolder: SurfaceHolder
) {
    private val factor: Float

    val width: Float
    val height: Float
    val bounds: RectF

    init {
        val surfaceSize = Point(
            surfaceHolder.surfaceFrame.width(),
            surfaceHolder.surfaceFrame.height()
        )

        factor = calculateGreatestCommonFactor(surfaceSize.x, surfaceSize.y)
        width = surfaceSize.x / factor
        height = surfaceSize.y / factor
        bounds = RectF(0f, 0f, width, height)

        Log.d(
            "Screen::init",
            "Virtual screen size: ${width}x${height} (${surfaceSize.x}x${surfaceSize.y}px)")
    }

    fun toScreenCoordinates(coordinates: PointF) = coordinates * factor

    fun toScreenCoordinates(rect: RectF) = rect * factor

    fun toCameraCoordinates(coordinates: PointF) = coordinates / factor

    // RectF no implementa operador de division.
    fun toCameraCoordinates(rect: RectF) = RectF(
        rect.left / factor,
        rect.top / factor,
        rect.right / factor,
        rect.bottom / factor,
    )

    private fun calculateGreatestCommonFactor(a: Int, b: Int): Float =
        if (b == 0)
            a.toFloat()
        else
            calculateGreatestCommonFactor(b, a % b)
}
