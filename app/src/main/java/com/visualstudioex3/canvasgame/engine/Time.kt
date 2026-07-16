package com.visualstudioex3.canvasgame.engine

/*
    Cronometro que permite calcular el tiempo de ejecucion del bucle para calultar el delta time
    asi como obtener el numero de cuadros por segundo que ha tardado.
*/
class Time {
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

        /*
            Se usa System.nanoTime() al ser la unica via, desde JVM, para obtener el tiempo con
            mayor precision. Desde nativo (C++) se pueden acceder a contadores de alta precision
            y su frecuencia para realizar correctamente estos calculos en aplicaciones multimedia y
            juegos.
         */
        private fun getTime(): Long = System.nanoTime()
    }
}
