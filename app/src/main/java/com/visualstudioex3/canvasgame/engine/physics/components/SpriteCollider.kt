package com.visualstudioex3.canvasgame.engine.physics.components

import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.IComponent
import com.visualstudioex3.canvasgame.engine.graphics.components.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.extensions.BitmapExtensions.Companion.getBounds

class SpriteCollider(
    override val gameObject: GameObject
) : IComponent {
    private var spriteRenderer: SpriteRenderer? = null

    override var enable: Boolean = true
    var bounds = RectF()
        private set

    var onCollision: ((GameObject) -> Unit)? = null

    override fun update(deltaTime: Float) {
        sync()
    }

    fun sync() {
        if (spriteRenderer == null)
            spriteRenderer = gameObject.getComponent<SpriteRenderer>()
        bounds = spriteRenderer?.image
            ?.getBounds(gameObject.transform.position) ?: RectF()
    }
}
