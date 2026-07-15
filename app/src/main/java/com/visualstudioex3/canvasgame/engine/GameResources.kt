package com.visualstudioex3.canvasgame.engine

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class GameResources {
    companion object {
        private lateinit var resources: Resources

        fun setResources(resources: Resources) {
            Companion.resources = resources
        }

        fun loadBitmap(resourceId: Int): Bitmap {
            try {
                return BitmapFactory.decodeResource(resources, resourceId)
            } catch (e: Exception) {
                error("Error al cargar el recurso como Bitmap: ${e.message}")
            }
        }
    }
}
