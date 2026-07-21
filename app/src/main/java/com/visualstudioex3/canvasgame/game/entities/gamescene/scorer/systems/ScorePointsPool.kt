package com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.systems

import com.visualstudioex3.canvasgame.engine.ObjectPool
import com.visualstudioex3.canvasgame.engine.scenes.SceneManager
import com.visualstudioex3.canvasgame.game.entities.gamescene.scorer.ScorePoints

class ScorePointsPool(
    instances: Int
): ObjectPool<ScorePoints>(instances) {
    override fun onCreateInstance(): ScorePoints =
        ScorePoints().apply {
            SceneManager.scene.gameObjects.add(this)
        }
}
