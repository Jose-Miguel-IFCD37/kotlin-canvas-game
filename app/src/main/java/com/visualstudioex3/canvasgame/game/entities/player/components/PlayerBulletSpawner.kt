package com.visualstudioex3.canvasgame.game.entities.player.components

import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.Time
import com.visualstudioex3.canvasgame.game.entities.player.PlayerBulletPool

class PlayerBulletSpawner(
    override val gameObject: GameObject
): IComponent {
    companion object {
        private const val MAX_INSTANCES: Int = 5
        private const val SHOOT_DELAY: Float = 0.25f
    }

    private val instances = PlayerBulletPool(MAX_INSTANCES)
    private var time: Float = Time.getTime()

    override var enable: Boolean = true

    override fun update(deltaTime: Float) {
        val now: Float = Time.getTime()
        if (now - time >= SHOOT_DELAY) {
            instances.getInstance().apply {
                this?.transform?.position = gameObject.transform.position
            }
            time = now
        }
    }
}
