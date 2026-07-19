package com.visualstudioex3.canvasgame.engine

class Time {
    companion object {
        private var endTime: Float = 0f
        private var deltaTime: Float = 0f

        var framesPerSecond: Float = 0f
            private set

        fun getTime(): Float =
            (System.nanoTime() / 1_000_000_000.0).toFloat()

        fun getDeltaTime(): Float {
            val currentTime: Float = getTime()

            deltaTime = currentTime - endTime
            endTime = currentTime
            framesPerSecond = 1f / deltaTime

            return deltaTime
        }
    }
}
