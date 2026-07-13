package com.visualstudioex3.canvasgame.engine

import android.graphics.Point
import android.graphics.PointF
import android.view.SurfaceHolder

class Screen(
    surfaceHolder: SurfaceHolder
) {
    private val aspectRatio: PointF

    val width: Float
    val height: Float

    init {
        val surfaceSize = Point(
            surfaceHolder.surfaceFrame.width(),
            surfaceHolder.surfaceFrame.height()
        )

        aspectRatio = getAspectRatio(surfaceSize)

        width = surfaceSize.x / aspectRatio.x
        height = surfaceSize.y / aspectRatio.y
    }

    fun toScreenCoordinates(coordinates: PointF) =
        PointF(
            coordinates.x * aspectRatio.x,
            coordinates.y * aspectRatio.y
        )

    fun toGameCoordinates(coordinates: PointF) =
        PointF(
            coordinates.x / aspectRatio.x,
            coordinates.y / aspectRatio.y
        )

    private fun getAspectRatio(size: Point): PointF {
        val surfaceSize: Point = highestFirst(size.x, size.y)
        val divisor: Float = gcd(surfaceSize.x, surfaceSize.y)

        return PointF(
            size.x / divisor,
            size.y / divisor
        )
    }

    private fun highestFirst(a: Int, b: Int): Point =
        if (a < b)
            Point(b, a)
        else
            Point(a, b)

    private fun gcd(a: Int, b: Int): Float =
        if (b == 0)
            a.toFloat()
        else
            gcd(b, a % b)
}
