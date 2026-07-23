package com.visualstudioex3.canvasgame.engine.audio

import android.media.MediaPlayer
import android.media.SoundPool
import com.visualstudioex3.canvasgame.engine.GameEngine

object AudioManager {
    const val DEFAULT_MAX_CHANNELS: Int = 16

    private var soundPool: SoundPool? = null
    private var musicTrackPlayer: MediaPlayer? = null

    fun initialize(maxChannels: Int = DEFAULT_MAX_CHANNELS) {
        if (soundPool == null) {
            try {
                soundPool = SoundPool.Builder()
                    .setMaxStreams(maxChannels)
                    .build()
            } catch (e: Exception) {
                error("Error al inicializar la instancia SoundPool: ${e.message}")
            }
        }
    }

    fun loadSoundFromResources(resourceId: Int): Int {
        try {
            return soundPool!!.load(
                GameEngine.getContext(),
                resourceId,
                0
            )
        } catch (e: Exception) {
            error("Error al cargar recurso de sonido: ${e.message}")
        }
    }

    fun playSound(soundId: Int): Int {
        try {
            return soundPool!!.play(
                soundId,
                1f,
                1f,
                0,
                0,
                1f
            )
        } catch (e: Exception) {
            error("Error al reproducir sonido: ${e.message}")
        }
    }

    fun stopSound(channel: Int) {
        try {
            soundPool!!.stop(channel)
        } catch (e: Exception) {
            error("Error al detener canal de sonido: ${e.message}")
        }
    }

    fun unloadSound(soundId: Int) {
        try {
            soundPool!!.unload(soundId)
        } catch (e: Exception) {
            error("Error al descargar recurso de sonido: ${e.message}")
        }
    }

    fun loadMusicFromResources(resourceId: Int) {
        if (musicTrackPlayer != null) {
            stopMusic()
        }

        try {
            musicTrackPlayer = MediaPlayer.create(
                GameEngine.getContext(),
                resourceId
            ).apply {
                isLooping = true
                prepare()
            }
        } catch (e: Exception) {
            error("Error al inicializar MediaPlayer: ${e.message}")
        }
    }

    fun playMusic() {
        try {
            musicTrackPlayer!!.start()
        } catch (e: Exception) {
            error("Error al reproducir pista de musica: ${e.message}")
        }
    }

    fun stopMusic() {
        try {
            musicTrackPlayer!!.stop()
        } catch (e: Exception) {
            error("Error al detener pista de musica: ${e.message}")
        }
    }

    fun unloadMusic() {
        try {
            musicTrackPlayer?.release()
        } catch (e: Exception) {
            error("Error al liberar MediaPlayer y descargar pista de musica: ${e.message}")
        } finally {
            musicTrackPlayer = null
        }
    }
}
