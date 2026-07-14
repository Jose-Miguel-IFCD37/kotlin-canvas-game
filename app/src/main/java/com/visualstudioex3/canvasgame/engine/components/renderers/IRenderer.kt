package com.visualstudioex3.canvasgame.engine.components.renderers

import com.visualstudioex3.canvasgame.engine.GameObject

interface IRenderer {
    val gameObject: GameObject

    fun draw()
}
