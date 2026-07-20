package com.visualstudioex3.canvasgame.engine

class Timer: IEnableState {
    private var time: Float = Time.getTime()

    override var enable: Boolean = true
        set(value) {
            field = value

            if (value)
                reset()
        }
    var interval: Float = 0f
    var onTime: (() -> Unit)? = null

    fun update() {
        if (enable) {
            val now: Float = Time.getTime()

            if (now - time >= interval) {
                onTime?.invoke()
                time = now
            }
        }
    }

    fun reset() {
        time = Time.getTime()
    }
}
