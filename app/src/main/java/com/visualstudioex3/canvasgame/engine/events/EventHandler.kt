package com.visualstudioex3.canvasgame.engine.events

open class EventHandler {
    private val observers = mutableListOf<IEventListener>()

    fun addObserver(observer: IEventListener) {
        observers.add(observer)
    }

    fun removeObserver(observer: IEventListener) {
        observers.remove(observer)
    }

    fun removeAllObservers() {
        observers.clear()
    }

    fun notifyObservers(event: IEvent) {
        observers.forEach {
            it.onEvent(event)
        }
    }
}
