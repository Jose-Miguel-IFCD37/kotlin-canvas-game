package com.visualstudioex3.canvasgame.engine

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate

abstract class GameObject {
    private var centerOffset = Offset.Zero

    var position = Offset.Zero
    var rotation: Float = 0f
    var sprite: ImageBitmap? = null
        set(value) {
            field = value

            if (value != null) {
                centerOffset = Offset(
                    value.width / 2f,
                    value.height / 2f
                )
            }
        }

    val hitBox: Rect
        get() {
            return if (sprite != null)
                Rect(
                    position - centerOffset,
                    Offset(
                        sprite?.width!!.toFloat(),
                        sprite?.height!!.toFloat()
                    )
                )
            else Rect.Zero
        }

    fun DrawScope.draw() {
        if (sprite != null)
            rotate(rotation) {
                drawImage(
                    sprite!!,
                    position - centerOffset
                )
            }
    }

    abstract suspend fun update()
}
