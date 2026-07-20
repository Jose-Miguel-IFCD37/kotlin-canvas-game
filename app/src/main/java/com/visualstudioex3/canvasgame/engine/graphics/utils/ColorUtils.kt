package com.visualstudioex3.canvasgame.engine.graphics.utils

import android.graphics.Color
import com.visualstudioex3.canvasgame.engine.MathF

class ColorUtils {
    companion object {
        fun lerp(start: Color, stop: Color, frequency: Float): Color = Color.valueOf(
            MathF.lerp(start.red(), stop.red(), frequency),
            MathF.lerp(start.green(), stop.green(), frequency),
            MathF.lerp(start.blue(), stop.blue(), frequency),
            MathF.lerp(start.alpha(), stop.alpha(), frequency)
        )
    }
}
