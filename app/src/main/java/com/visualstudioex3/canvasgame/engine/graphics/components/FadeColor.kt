package com.visualstudioex3.canvasgame.engine.graphics.components

import android.graphics.Color
import androidx.core.graphics.toColor
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.graphics.utils.ColorUtils

class FadeColor(
    override val gameObject: GameObject
) : IComponent {
    override var enable: Boolean = true
    var startColor: Color = Color.BLACK.toColor()
        set(value) {
            field = value
            currentColor = value
        }
    var stopColor: Color = Color.BLACK.toColor()
    var speed: Float = 1f
    var currentColor: Color = Color.BLACK.toColor()
        private set
    var onComplete: (() -> Unit)? = null

    override fun update(deltaTime: Float) {
        if (enable) {
            currentColor = ColorUtils.lerp(
                currentColor,
                stopColor,
                deltaTime * speed
            )

            if (currentColor == stopColor) {
                onComplete?.invoke()
                enable = false
            }
        }
    }

    fun reset() {
        currentColor = startColor
    }
}
