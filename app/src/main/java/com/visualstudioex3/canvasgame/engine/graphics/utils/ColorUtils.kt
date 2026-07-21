package com.visualstudioex3.canvasgame.engine.graphics.utils

import android.graphics.Color
import com.visualstudioex3.canvasgame.engine.MathF

object ColorUtils {
    fun lerp(start: Color, stop: Color, fraction: Float): Color = Color.valueOf(
        MathF.lerp(start.red(), stop.red(), fraction),
        MathF.lerp(start.green(), stop.green(), fraction),
        MathF.lerp(start.blue(), stop.blue(), fraction),
        MathF.lerp(start.alpha(), stop.alpha(), fraction)
    )
}
