package com.visualstudioex3.canvasgame.game.entities

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IEnableState
import com.visualstudioex3.canvasgame.engine.MathF
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.engine.graphics.components.CircleRenderer

class Explossion: GameObject(), IEnableState {
    companion object {
        private const val MAX_RADIUS: Float = 25f
    }

    private val circle = addComponent<CircleRenderer>()
    private val time: Float = Time.getTime()

    var speed: Float = 50f

    override fun onUpdate(deltaTime: Float) {
        MathF.lerp(circle.radius, MAX_RADIUS, deltaTime)

        if (circle.radius >= MAX_RADIUS)
            enable = false
    }
}
