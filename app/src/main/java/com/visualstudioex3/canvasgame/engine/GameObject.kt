package com.visualstudioex3.canvasgame.engine

import com.visualstudioex3.canvasgame.engine.components.renderers.IRenderer

abstract class GameObject {
    val transform = Transform()
    var renderer: IRenderer? = null
    var collider: Any? = null
    var inputTouch: Any? = null

    var enabled: Boolean = true

    abstract fun onUpdate(deltaTime: Float)

    fun draw() {
        renderer?.draw()
    }
}
