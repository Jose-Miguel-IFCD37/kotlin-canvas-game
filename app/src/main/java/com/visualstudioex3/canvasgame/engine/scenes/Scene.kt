package com.visualstudioex3.canvasgame.engine.scenes

import com.visualstudioex3.canvasgame.engine.GameObject

abstract class Scene {
    val gameObjects = mutableListOf<GameObject>()
    val services = HashSet<IService>()

    inline fun <reified T> addService(): T
            where T : IService {
        val instance: T =
            T::class.java
                .getDeclaredConstructor()
                .newInstance() as T

        services.add(instance)

        return instance
    }

    inline fun <reified T> addGameObject(): T
            where T : GameObject {
        val instance: T =
            T::class.java
                .getDeclaredConstructor()
                .newInstance() as T

        gameObjects.add(instance)

        return instance
    }

    inline fun <reified T> getService(): T?
            where T : IService =
        services.firstOrNull {
            it::class.java == T::class.java
        } as? T

    inline fun <reified T> getRequiredService(): T
            where T : IService =
        getService() ?: error("No se encuentra servicio de tipo ${T::class.simpleName}")

    inline fun <reified T> findGameObject(): T?
            where T : GameObject =
        gameObjects.firstOrNull {
            it::class.java == T::class.java
        } as? T

    inline fun <reified T> findRequiredGameObject(): T
            where T : GameObject =
        findGameObject()
            ?: error("No se encuentra ningun GameObject de tipo  ${T::class.simpleName}")

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> findGameObjects(): List<T>
            where T : GameObject =
        gameObjects.filter {
            it::class.java == T::class.java
        } as List<T>

    abstract fun onCreate()

    abstract fun onDestroy()

    fun initialize() {
        onCreate()

        gameObjects.forEach {
            if (it.enable)
                it.onEnable()
        }
    }

    fun onFrame(deltaTime: Float) {
        update(deltaTime)
        draw()
    }

    private fun update(deltaTime: Float) {
        gameObjects.forEach {
            if (it.enable)
                it.update(deltaTime)
        }
    }

    private fun draw() {
        gameObjects.forEach {
            if (it.enable)
                it.draw()
        }
    }
}
