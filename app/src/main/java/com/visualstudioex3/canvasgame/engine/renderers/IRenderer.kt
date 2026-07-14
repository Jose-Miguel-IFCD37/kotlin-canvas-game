package com.visualstudioex3.canvasgame.engine.renderers

import com.visualstudioex3.canvasgame.engine.GameObject

interface IRenderer {
    val gameObject: GameObject

    fun draw()
}
