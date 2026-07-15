package com.visualstudioex3.canvasgame.game.entities

import android.graphics.PointF
import android.util.Log
import com.visualstudioex3.canvasgame.R
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.GameResources
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.GameRender

class Player: GameObject() {
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

    override fun onUpdate() {
    }
}
