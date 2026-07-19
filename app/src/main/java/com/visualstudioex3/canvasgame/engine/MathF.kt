package com.visualstudioex3.canvasgame.engine

class MathF {
    companion object {
        fun max(a: Float, b: Float): Float =
            kotlin.math.max(a, b)

        fun min(a: Float, b: Float): Float =
            kotlin.math.min(a, b)

        fun clamp(value: Float, min: Float, max: Float): Float =
            java.lang.Math.clamp(value, min, max)

        fun lerp(start: Float, stop: Float, fraction: Float): Float =
            androidx.compose.ui.util.lerp(start, stop, fraction)
    }
}
