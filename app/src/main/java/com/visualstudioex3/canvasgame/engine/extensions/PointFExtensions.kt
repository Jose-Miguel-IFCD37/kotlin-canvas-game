package com.visualstudioex3.canvasgame.engine.extensions

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class PointFExtensions {
    companion object {
        fun PointF.toCameraCoordinates(): PointF =
            RenderManager.camera.toCameraCoordinates(this)

        fun PointF.toScreenCoordinates(): PointF =
            RenderManager.camera.toScreenCoordinates(this)
    }
}
