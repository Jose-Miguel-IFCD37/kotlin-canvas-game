package com.visualstudioex3.canvasgame.engine

object MathF {
    const val EPSILON: Float = 1.1754944E-38f

    fun max(a: Float, b: Float): Float =
        kotlin.math.max(a, b)

    fun min(a: Float, b: Float): Float =
        kotlin.math.min(a, b)

    fun clamp(value: Float, min: Float, max: Float): Float =
        java.lang.Math.clamp(value, min, max)

    fun lerp(start: Float, stop: Float, fraction: Float): Float =
        androidx.compose.ui.util.lerp(start, stop, fraction)

    fun abs(value: Float): Float =
        kotlin.math.abs(value)

    // Basado en la implementacion de Unity Mathf.Approximately:
    // https://docs.unity3d.com/6000.0/Documentation/ScriptReference/Mathf.Approximately.html
    // https://github.com/Unity-Technologies/UnityCsReference/blob/master/Runtime/Export/Math/Mathf.cs#L263
    fun approximately(a: Float, b: Float): Boolean =
        this.abs(a - b) <
                this.max(
                    0.000001f * this.max(
                        this.abs(a),
                        this.abs(b)
                    ),
                    EPSILON
                )

    // Basado en la funcion de Unity Mathf.PingPong:
    // https://docs.unity3d.com/6000.0/Documentation/ScriptReference/Mathf.PingPong.html
    fun pingPong(value: Float, length: Float): Float =
        length - this.abs((this.abs(value) % (2f * length)) - length)
}
