package com.visualstudioex3.canvasgame.engine.components.events

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.IComponent

open class EventHandler(
    override val gameObject: GameObject
) : IComponent {
    private var eventRaised: Boolean = false
    override var enable: Boolean = true

    var condition: () -> Boolean = { true }
    var onEvent: () -> Unit = {}
    var onEnter: () -> Unit = {}
    var onExit: () -> Unit = {}

    override fun update(deltaTime: Float) {
        if (condition()) {
            onEvent()
            if (!eventRaised) {
                onEnter()
                eventRaised = true
            } else {
                onExit()
                eventRaised = false
            }
        }
    }
}
