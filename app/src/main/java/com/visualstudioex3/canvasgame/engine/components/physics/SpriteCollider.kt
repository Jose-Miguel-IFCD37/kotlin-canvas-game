package com.visualstudioex3.canvasgame.engine.components.physics

import android.graphics.RectF
import com.visualstudioex3.canvasgame.engine.GameObject
import com.visualstudioex3.canvasgame.engine.components.IComponent
import com.visualstudioex3.canvasgame.engine.components.renderers.SpriteRenderer
import com.visualstudioex3.canvasgame.engine.graphics.extensions.BitmapExtensions.Companion.getBounds

class SpriteCollider(
    override val gameObject: GameObject
) : IComponent {
    private var spriteRenderer: SpriteRenderer? = null

    override var enable: Boolean = true
    var bounds = RectF()
        private set

    override fun update(deltaTime: Float) {
        if (spriteRenderer == null)
            spriteRenderer = gameObject.getComponent<SpriteRenderer>()
        bounds = spriteRenderer?.image
            ?.getBounds(gameObject.transform.position) ?: RectF()
    }
}
