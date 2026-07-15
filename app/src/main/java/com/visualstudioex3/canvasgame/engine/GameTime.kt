package com.visualstudioex3.canvasgame.engine

class GameTime {
    companion object {
        private var oldTime: Long = getTime()
        private var elapsedTime: Long = 0

        fun getDeltaTime(): Float {
            val currentTime: Long = getTime()

            elapsedTime = currentTime - oldTime
            oldTime = currentTime

            return (elapsedTime / 1_000_000_000.0).toFloat()
        }

        private fun getTime(): Long = System.nanoTime()
    }
}
