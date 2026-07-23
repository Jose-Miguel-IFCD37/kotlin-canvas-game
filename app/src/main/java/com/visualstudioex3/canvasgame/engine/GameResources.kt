package com.visualstudioex3.canvasgame.engine

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.visualstudioex3.canvasgame.engine.audio.AudioManager
import com.visualstudioex3.canvasgame.engine.audio.MusicTrack
import com.visualstudioex3.canvasgame.engine.audio.SoundEffect

object GameResources {
    private val resources: Resources = GameEngine.getContext().resources
    private val bitmapReferences = mutableListOf<Bitmap>()
    private val soundEffectReferences = mutableListOf<Int>()

    fun loadBitmap(resourceId: Int): Bitmap {
        try {
            return BitmapFactory.decodeResource(resources, resourceId).apply {
                bitmapReferences.add(this)
            }

        } catch (e: Exception) {
            error("Error al cargar el recurso como Bitmap: ${e.message}")
        }
    }

    fun getString(resourceId: Int): String {
        try {
            return resources.getString(resourceId)
        } catch (e: Exception) {
            error("Error al cargar el recurso como String: ${e.message}")
        }
    }

    fun loadSoundEffect(resourceId: Int): SoundEffect {
        try {
            return SoundEffect(
                AudioManager.loadSoundFromResources(resourceId).apply {
                    soundEffectReferences.add(this)
                }
            )
        } catch (e: Exception) {
            error("Error al cargar el recurso como SoundEffect: ${e.message}")
        }
    }

    fun loadMusicTrack(resourceId: Int): MusicTrack {
        try {
            // Esta llamada la usamos para verificar la existencia del recurso:
            resources.getResourceName(resourceId)

            // MediaPlayer carga por si mismo el recurso de audio, por lo que
            // simplemente devolveremos una instancia de MusicTrack con el valor de
            // resourceId.
            return MusicTrack(resourceId)
        } catch (e: Exception) {
            error("Error al cargar el recurso como MusicTrack: ${e.message}")
        }
    }

    fun release() {
        releaseBitmaps()
        releaseSoundEffects()
        releaseMusicTrack()
    }

    private fun releaseBitmaps() {
        bitmapReferences.forEach {
            it.recycle()
        }
        bitmapReferences.clear()
    }

    private fun releaseSoundEffects() {
        soundEffectReferences.forEach {
            AudioManager.unloadSound(it)
        }
        soundEffectReferences.clear()
    }

    private fun releaseMusicTrack() {
        AudioManager.unloadMusic()
    }
}
