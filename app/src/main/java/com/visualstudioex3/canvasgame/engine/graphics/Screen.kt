package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import android.view.SurfaceHolder
import androidx.core.graphics.div
import androidx.core.graphics.times

class Screen(
    surfaceHolder: SurfaceHolder
) {
    private val factor: Float

    val width: Float
    val height: Float

    init {
        val surfaceSize: Point = sortHighestFirst(
            surfaceHolder.surfaceFrame.width(),
            surfaceHolder.surfaceFrame.height()
        )

        factor = calculateGreatestCommonFactor(surfaceSize.x, surfaceSize.y)
        width = surfaceSize.x / factor
        height = surfaceSize.y / factor

        Log.d(
            "Screen::init",
            "Virtual screen size: ${width}x${height} (${surfaceSize.x}x${surfaceSize.y}px)")
    }

    fun toScreenCoordinates(coordinates: PointF) = coordinates * factor

    fun toGameCoordinates(coordinates: PointF) = coordinates / factor

    private fun sortHighestFirst(a: Int, b: Int): Point =
        if (a < b)
            Point(b, a)
        else
            Point(a, b)

    private fun calculateGreatestCommonFactor(a: Int, b: Int): Float =
        if (b == 0)
            a.toFloat()
        else
            calculateGreatestCommonFactor(b, a % b)
}
