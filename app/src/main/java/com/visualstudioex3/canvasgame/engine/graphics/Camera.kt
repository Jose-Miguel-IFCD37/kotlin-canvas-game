package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Point
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceHolder
import androidx.core.graphics.div
import androidx.core.graphics.times

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
