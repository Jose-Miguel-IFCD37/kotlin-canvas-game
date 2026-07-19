package com.visualstudioex3.canvasgame.engine.input.components

import android.graphics.PointF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.input.InputManager
import com.visualstudioex3.canvasgame.engine.input.InputState
import com.visualstudioex3.canvasgame.engine.input.TouchGestures

class InputTouch(
    override val gameObject: GameObject
) : IComponent {
    override var enable: Boolean = true

    var onTap: (offset: PointF) -> Unit = {}
    var onDoubleTap: (offset: PointF) -> Unit = {}
    var onLongPress: (offset: PointF) -> Unit = {}

    override fun update(deltaTime: Float) {
        val state: InputState = InputManager.getInputState()

        when (state.gesture) {
            TouchGestures.Tap -> onTap(state.offset)
            TouchGestures.DoubleTap -> onDoubleTap(state.offset)
            TouchGestures.LongPress -> onLongPress(state.offset)
            TouchGestures.None -> {}
        }
    }
}
