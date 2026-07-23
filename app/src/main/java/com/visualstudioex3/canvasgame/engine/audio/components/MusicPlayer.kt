package com.visualstudioex3.canvasgame.engine.audio.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.audio.AudioManager
import com.visualstudioex3.canvasgame.engine.audio.MusicTrack

class MusicPlayer(
    override val gameObject: GameObject
): IComponent {
    private var initializeMusicPlayer: Boolean = false

    override var enable: Boolean = true
        set(value) {
            field = value

            if (!field) {
                stop()
            }
        }
    var track: MusicTrack? = null
        set(value) {
            field = value
            initializeMusicPlayer = true
        }

    fun play() {
        initMusicPlayer()
        AudioManager.playMusic()
    }

    fun stop() {
        AudioManager.stopMusic()
    }

    override fun update(deltaTime: Float) {
    }

    override fun onDestroy() {
        stop()
    }

    private fun initMusicPlayer() {
        if (initializeMusicPlayer) {
            AudioManager.unloadMusic()
            AudioManager.loadSoundFromResources(track!!.resourceId)
            initializeMusicPlayer = false
        }
    }
}
