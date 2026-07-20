package com.visualstudioex3.canvasgame.engine

class Timer {
    private var time: Float = Time.getTime()

    var interval: Float = 0f

    var onTime: (() -> Unit)? = null

    fun update() {
        val now: Float = Time.getTime()

        if (now - time >= interval) {
            onTime?.invoke()
            time = now
        }
    }
}
