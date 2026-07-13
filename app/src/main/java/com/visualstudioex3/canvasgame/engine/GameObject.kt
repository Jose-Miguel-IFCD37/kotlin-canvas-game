package com.visualstudioex3.canvasgame.engine

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.visualstudioex3.canvasgame.engine.renderers.IRenderer

abstract class GameObject {
    // TODO: Implement Transform Unity like component for position, rotation and scale logic.
    // TODO: Implement abstract Renderer Unity like component, use for implements:
    //  - SpriteRenderer
    //  - TextRenderer
    //  - LineRenderer
    // TODO: Implement Collider Unity like component for manage basic box collisions.
    // TODO: Implement InputTouch component to manage the input basic actions.

    val transform = Transform()
    var renderer: IRenderer? = null
    var collider: Any? = null
    var inputTouch: Any? = null

    var enabled: Boolean = true

    abstract fun update()

    fun DrawScope.draw() {
        renderer?.apply {
            draw()
        }
    }
}
