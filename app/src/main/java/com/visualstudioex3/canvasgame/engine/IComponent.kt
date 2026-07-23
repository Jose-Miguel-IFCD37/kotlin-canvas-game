package com.visualstudioex3.canvasgame.engine

interface IComponent: IEnableState {
    val gameObject: GameObject
    override var enable: Boolean

    fun update(deltaTime: Float)

    open fun onDestroy() {
    }
}
