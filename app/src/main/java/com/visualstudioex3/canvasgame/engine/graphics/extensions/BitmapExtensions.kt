package com.visualstudioex3.canvasgame.engine.graphics.extensions

import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.div
import androidx.core.graphics.minus
import androidx.core.graphics.plus
import com.visualstudioex3.canvasgame.engine.extensions.PointFExtensions.Companion.toCameraCoordinates

class BitmapExtensions {
    companion object {
        fun Bitmap.getSize() = PointF(
            width.toFloat(),
            height.toFloat()
        ).toCameraCoordinates()

        fun Bitmap.getBounds(center: PointF = PointF()): RectF {
            val size: PointF = getSize()
            val leftTop: PointF = center - (size / 2f)
            val rightBottom: PointF = leftTop + size

            return RectF(
                leftTop.x,
                leftTop.y,
                rightBottom.x,
                rightBottom.y
            )
        }
    }
}
