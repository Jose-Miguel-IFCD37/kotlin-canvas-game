package com.visualstudioex3.canvasgame.game.services.explossion

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.scenes.IService

class ExplossionFactory: IService {
    val pool = ExplossionPool(5)

    fun explode(position: PointF) {
        pool.getInstance().apply {
            this?.transform?.position = position
        }
    }
}
