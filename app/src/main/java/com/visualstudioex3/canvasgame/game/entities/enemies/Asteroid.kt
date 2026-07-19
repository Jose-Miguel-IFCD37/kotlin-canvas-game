package com.visualstudioex3.canvasgame.game.entities.enemies

import com.visualstudioex3.canvasgame.R

class Asteroid: BaseEnemy() {
    init {
        addSprite(R.drawable.asteroid_1)
        addSprite(R.drawable.asteroid_2)
        addSprite(R.drawable.asteroid_3)
        addSprite(R.drawable.asteroid_4)
    }

    override fun onUpdate(deltaTime: Float) {
        super.onUpdate(deltaTime)

        transform.rotation += deltaTime * 45f
    }
}
