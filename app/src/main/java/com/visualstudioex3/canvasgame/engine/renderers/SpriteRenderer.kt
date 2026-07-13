package com.visualstudioex3.canvasgame.engine.renderers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.Transform

class SpriteRenderer(
    override val gameObject: GameObject
) : IRenderer {
    var image: ImageBitmap? = null

    override fun DrawScope.draw() {
        if (image != null) {
            val transform: Transform = gameObject.transform

            rotate(transform.rotation, transform.position) {
                scale(
                    transform.scale,
                    transform.scale,
                    transform.position
                ) {
                    val centerOffset = Offset(
                        image?.width!!.toFloat(),
                        image?.height!!.toFloat()
                    ) / 2f

                    drawImage(image!!, transform.position - centerOffset)
                }
            }
        }
    }
}
