package com.visualstudioex3.canvasgame.engine

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.components.IComponent
import com.visualstudioex3.canvasgame.engine.components.renderers.IRenderer

abstract class GameObject {
    val transform = Transform()
    val components = mutableListOf<IComponent>()
    var enabled: Boolean = true

    inline fun <reified T> addComponent(): T
            where T : IComponent {
        val instance: T =
            T::class.java
                .getDeclaredConstructor(GameObject::class.java)
                .newInstance(this) as T

        components.add(instance)

        return instance
    }

    inline fun <reified T> getComponent(): T?
            where T : IComponent =
        components.firstOrNull { it::class.java == T::class.java } as? T

    abstract fun onUpdate(deltaTime: Float)
    open fun onDraw() {
    }

    fun update(deltaTime: Float) {
        components.forEach { it.update(deltaTime) }
        onUpdate(deltaTime)
    }

    fun draw() {
        components.forEach { if (it is IRenderer) it.draw() }
        onDraw()
    }
}
