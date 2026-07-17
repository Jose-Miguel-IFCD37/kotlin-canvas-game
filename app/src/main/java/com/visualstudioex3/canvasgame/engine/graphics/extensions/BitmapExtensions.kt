package com.visualstudioex3.canvasgame.engine.graphics.extensions

import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.div
import androidx.core.graphics.minus
import androidx.core.graphics.plus
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class BitmapExtensions {
    companion object {
        fun Bitmap.getBounds(center: PointF): RectF {
            val size: PointF =
                RenderManager.camera.toCameraCoordinates(
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
