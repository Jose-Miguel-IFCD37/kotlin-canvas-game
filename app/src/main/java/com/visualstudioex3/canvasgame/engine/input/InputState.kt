package com.visualstudioex3.canvasgame.engine.input

import android.graphics.PointF

enum class TouchGestures {
    None,
    Tap,
    DoubleTap,
    LongPress
}

data class InputState(
    val gesture: TouchGestures,
    val offset: PointF
) {
    companion object {
        val NONE = InputState(
            TouchGestures.None,
            offset = PointF()
        )
    }
}
