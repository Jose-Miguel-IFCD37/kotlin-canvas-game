package com.visualstudioex3.canvasgame.engine

/*
    En videojuegos y programas multimedia se busca evitar la continua creacion y destruccion de
    instancias de objetos, sobre todo en lenguajes como Kotlin con gestion automatizada de memoria
    (garbage collector). En su lugar se utilizan listas de objetos reciclables usando el patron
    Object Pool. Este gestiona uan lista fija e inicializada de instancias de un tipo concreto y
    permite solicitar la primera instancia disponible (desactivada o marcada como libre) de la lista.
 */
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
