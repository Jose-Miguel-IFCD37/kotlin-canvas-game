package com.visualstudioex3.canvasgame.engine

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

class ImageAssets {
    companion object {
        private val textures = mutableMapOf<Int, ImageBitmap>()

        fun getImage(id: Int): ImageBitmap =
            textures[id] ?: error("La imagen con id: $id no existe.")
    }

    @Composable
    fun LoadImages(vararg ids: Int) {
        for (id in ids) {
            val image = ImageBitmap.imageResource(id)
            textures[id] = image
        }
    }
}
