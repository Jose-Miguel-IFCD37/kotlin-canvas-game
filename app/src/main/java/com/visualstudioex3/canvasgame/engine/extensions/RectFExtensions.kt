package com.visualstudioex3.canvasgame.engine.extensions

import android.graphics.PointF
import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class RectFExtensions {
    companion object {
        fun RectF.getCenter() = PointF(centerX(), centerY())

        fun RectF.toCameraCoordinates(): RectF =
            RenderManager.camera.toCameraCoordinates(this)

        fun RectF.toScreenCoordinates(): RectF =
            RenderManager.camera.toScreenCoordinates(this)
    }
}
