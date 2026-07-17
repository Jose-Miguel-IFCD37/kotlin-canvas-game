package com.visualstudioex3.canvasgame.engine.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState

interface IComponent: IEnableState {
    val gameObject: GameObject
    override var enable: Boolean

    fun update(deltaTime: Float)
}
