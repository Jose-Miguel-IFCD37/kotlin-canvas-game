package com.visualstudioex3.canvasgame.engine

import com.visualstudioex3.canvasgame.engine.graphics.components.IRenderer
import com.visualstudioex3.canvasgame.engine.scenes.IService
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager

/*
    GameObject basado en la implementacion de Unity y su arquitectura de componentes.
    https://docs.unity3d.com/6000.0/Documentation/ScriptReference/GameObject.html
 */
abstract class GameObject {
    val transform = Transform()
    val components = mutableListOf<IComponent>()

    var enable: Boolean = true
        set(value) {
            field = value
            if (enable)
                onEnable()
            else
                onDisable()
        }

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
        components.firstOrNull {
            it::class.java == T::class.java
        } as? T

    inline fun <reified T> getService(): T?
            where T : IService =
        SceneManager.scene.getService()

    inline fun <reified T> getRequiredService(): T
            where T : IService =
        SceneManager.scene.getRequiredService()

    inline fun <reified T> findGameObject(): T?
            where T : GameObject =
        SceneManager.scene.findGameObject()

    inline fun <reified T> findRequiredGameObject(): T
            where T : GameObject =
        SceneManager.scene.findRequiredGameObject()

    inline fun <reified T> findGameObjects(): List<T>
            where T : GameObject =
        SceneManager.scene.findGameObjects()

    open fun onEnable() {
    }

    open fun onDisable() {
    }

    abstract fun onUpdate(deltaTime: Float)

    fun update(deltaTime: Float) {
        components.forEach {
            if (it.enable)
                it.update(deltaTime)
        }
        onUpdate(deltaTime)
    }

    fun draw() {
        components.forEach {
            if (it is IRenderer && it.enable)
                it.draw()
        }
    }
}
