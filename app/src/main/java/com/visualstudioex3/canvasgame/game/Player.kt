package com.visualstudioex3.canvasgame.game

import android.graphics.PointF
import android.util.Log
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer

class Player: GameObject() {
    var i: Int = 0

    init {
        renderer = SpriteRenderer(this)
//        (renderer as SpriteRenderer).image =
//            ImageBitmap.imageResource(
//                GameEngine.globalSettings.appResources,
//                R.drawable.player_ship
//            )
        transform.position = PointF(3f, 5f)
        transform.scale = 5f
        transform.rotation = 45f
    }

    override fun update() {
//        if (i < 5) {
//            i++
//            transform.move(2.5f)
//            Log.d("player_loop", "Move iteration $i")
//        }
    }
}
