package com.visualstudioex3.canvasgame.engine

class GameTime {
    companion object {
        private var endTime: Long = 0
        private var deltaTime: Float = 0f

        var framesPerSecond: Float = 0f
            private set

        fun getDeltaTime(): Float {
            val currentTime: Long = getTime()

            deltaTime = ((currentTime - endTime) / 1_000_000_000.0).toFloat()
            endTime = currentTime
            framesPerSecond = 1f / deltaTime

            return deltaTime
        }

        private fun getTime(): Long = System.nanoTime()
    }
}
