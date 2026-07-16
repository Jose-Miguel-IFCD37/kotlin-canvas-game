package com.visualstudioex3.canvasgame.engine.components

import com.visualstudioex3.canvasgame.engine.GameObject

interface IComponent {
    val gameObject: GameObject
    var enable: Boolean

    fun update(deltaTime: Float)
}
