package com.visualstudioex3.canvasgame.engine.physics

import com.visualstudioex3.canvasgame.engine.physics.components.SpriteCollider
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager

class PhysicsManager {
    fun update() {
        val colliders: List<SpriteCollider> = getActiveColliders()

        for (current: SpriteCollider in colliders) {
            colliders.forEach {
                if (it != current) {
                    if (current.bounds.intersect(it.bounds)) {
                        it.onCollision?.invoke(it.gameObject)
                    }
                }
            }
        }
    }

    private fun getActiveColliders(): List<SpriteCollider> =
        SceneManager.scene.gameObjects.mapNotNull {
            it.getComponent<SpriteCollider>()
        }.filter {
            it.enable
        }
}
