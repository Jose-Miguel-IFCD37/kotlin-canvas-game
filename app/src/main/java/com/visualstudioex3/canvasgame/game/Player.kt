package com.visualstudioex3.canvasgame.game

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameEngine
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.renderers.SpriteRenderer

class Player: GameObject() {
    var i: Int = 0

    init {
        renderer = SpriteRenderer(this)
        (renderer as SpriteRenderer).image =
            ImageBitmap.imageResource(
                GameEngine.globalSettings.appResources,
                R.drawable.player_ship
            )
        transform.position = Offset(500f, 1000f)
        transform.scale = 5f
        transform.rotation = 45f
    }

    override fun update() {
        if (i < 5) {
            i++
            transform.move(50f)
            Log.d("player_loop", "Move iteration $i")
        }
    }
}
