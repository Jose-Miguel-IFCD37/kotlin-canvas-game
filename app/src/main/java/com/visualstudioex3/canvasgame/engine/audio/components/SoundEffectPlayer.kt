package com.visualstudioex3.canvasgame.engine.audio.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.audio.AudioManager
import com.visualstudioex3.canvasgame.engine.audio.SoundEffect

class SoundEffectPlayer(
    override val gameObject: GameObject
) : IComponent {
    private var channel: Int = 0

    override var enable: Boolean = true
        set(value) {
            field = value

            if (!field) {
                stop()
            }
        }
    var soundEffect: SoundEffect? = null

    fun play() {
        channel = AudioManager.playSound(
            soundEffect?.id
                ?: error("No se ha asignado ningun SoundEffect a este componente.")
        )
    }

    fun stop() {
        AudioManager.stopSound(channel)
    }

    override fun update(deltaTime: Float) {
    }

    override fun onDestroy() {
        stop()
    }
}
