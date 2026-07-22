package com.visualstudioex3.canvasgame.game.services.events

import com.visualstudioex3.canvasgame.engine.GameService
import com.visualstudioex3.canvasgame.engine.events.EventHandler
import com.visualstudioex3.canvasgame.engine.events.IEvent
import com.visualstudioex3.canvasgame.engine.events.IEventListener

class GameObserver: GameService() {
    private val eventHandler = EventHandler()

    fun addListener(observer: IEventListener) {
        eventHandler.addObserver(observer)
    }

    fun notifyEvent(event: IEvent) {
        eventHandler.notifyObservers(event)
    }
}
