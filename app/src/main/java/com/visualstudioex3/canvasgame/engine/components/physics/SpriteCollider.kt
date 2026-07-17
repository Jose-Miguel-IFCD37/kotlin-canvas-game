package com.visualstudioex3.canvasgame.engine.components.physics

import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.events.EventHandler
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.BitmapExtensions.Companion.getBounds
import com.visualstudioex3.canvasgame.engine.graphics.RenderManager

class SpriteCollider(
    override val gameObject: GameObject
) : EventHandler(gameObject) {
    private var spriteRenderer = gameObject.getComponent<SpriteRenderer>()

    override var enable: Boolean = true
    var bounds = RectF()
        private set

    var onCollision: (GameObject) -> Unit = {}
    var onCollisionEnter: (GameObject) -> Unit = {}
    var onCollisionExit: (GameObject) -> Unit = {}
    var onExitCameraBounds: () -> Unit = {}
    var onEnterCameraBounds: () -> Unit = {}

    init {
        condition = {
            RenderManager.camera.bounds.intersect(bounds)
        }
        onEnter = onEnterCameraBounds
        onExit = onExitCameraBounds
    }

    override fun update(deltaTime: Float) {
        bounds = spriteRenderer?.image
            ?.getBounds(gameObject.transform.position) ?: RectF()
    }
}
