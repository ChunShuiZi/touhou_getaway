package org.thcg.screen

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Enemy(
    val enemyPositionX: Float,
    var enemyPositionY: Float,
    private val width: Float,
    private val height: Float,
    private val speedY: Float
) {
    companion object {
        private val ENEMY_COLOR: Color = Color.BLUE
    }

    fun update(delta: Float) {
        enemyPositionY -= speedY * delta
    }

    fun draw(shapeRenderer: ShapeRenderer) {
        shapeRenderer.color = ENEMY_COLOR
        shapeRenderer.rect(enemyPositionX, enemyPositionY, width, height)
    }

    fun enemyIsOutOfScreen(screenHeight: Float): Boolean {
        return enemyPositionY + height < 0f
    }

    fun enemyIsHitByBullet(bullet: Bullet): Boolean {
        return bullet.x >= enemyPositionX && bullet.x <= enemyPositionX + width && bullet.y >= enemyPositionY && bullet.y <= enemyPositionY + height
    }
}