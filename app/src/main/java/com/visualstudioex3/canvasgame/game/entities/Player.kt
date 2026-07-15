package com.visualstudioex3.canvasgame.game.entities

import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.BitmapExtensions.Companion.getBounds
import com.visualstudioex3.canvasgame.engine.graphics.GameRender

class Player : GameObject() {
    init {
        renderer = SpriteRenderer(this)
        (renderer as SpriteRenderer).image =
            GameResources.loadBitmap(R.drawable.player_ship)

        transform.position = PointF(
            GameRender.screen.width / 2,
            GameRender.screen.height - 1f
        )

        Log.d("Player::init", "${transform.position}")
    }

    override fun onUpdate(deltaTime: Float) {
    }

    override fun onDraw() {
        (renderer as SpriteRenderer).let {
            if (it.image != null) {
                GameRender.primitives.drawRect(
                    it.image?.getBounds(transform.position)!!,
                    transform.scale,
                    color = Color.valueOf(1f, 0f, 0f, .25f),
                )
            }
        }
    }
}
