package com.visualstudioex3.canvasgame.engine.graphics

import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.div
import androidx.core.graphics.minus
import androidx.core.graphics.plus

class BitmapExtensions {
    companion object {
        fun Bitmap.getBounds(center: PointF): RectF {
            val size: PointF = GameRender.screen.toGameCoordinates(
                PointF(
                    width.toFloat(),
                    height.toFloat()
                )
            )
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
