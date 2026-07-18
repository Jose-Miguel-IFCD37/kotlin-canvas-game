package com.visualstudioex3.canvasgame.engine.input

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class InputManager {
    companion object {
        private var cacheState = InputState.NONE
        private var currentState = InputState.NONE

        fun getInputState(): InputState = currentState
    }

    fun cacheState(gesture: TouchGestures, offset: PointF) {
        cacheState = InputState(gesture, RenderManager.camera.toCameraCoordinates(offset))
    }

    fun update() {
        currentState = cacheState
        cacheState = InputState.NONE
    }
}
