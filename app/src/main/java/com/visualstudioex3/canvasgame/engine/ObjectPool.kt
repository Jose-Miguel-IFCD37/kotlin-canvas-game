package com.visualstudioex3.canvasgame.engine

abstract class ObjectPool<T : IEnableState>(
    instances: Int
) {
    private val pool = buildList {
        repeat(instances) {
            add(
                onCreateInstance().apply {
                    enable = false
                }
            )
        }
    }

    val count: Int = instances
    val activeCount: Int = pool.count { it.enable }
    val disableCount: Int = pool.count { !it.enable }

    abstract fun onCreateInstance(): T

    fun getInstance(): T? = pool
        .firstOrNull {
            !it.enable
        }.let {
            it?.enable = true

            return it
        }

    fun releaseAll() {
        pool.forEach { it.enable = false }
    }
}
