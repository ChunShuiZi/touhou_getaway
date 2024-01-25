package org.thcg.screen

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Enemy(
    private val x: Float,
    private var y: Float,
    private val width: Float,
    private val height: Float,
    private val speedY: Float
) {
    companion object {
        private val ENEMY_COLOR: Color = Color.BLUE
    }

    fun update(delta: Float) {
        y -= speedY * delta
    }

    fun draw(shapeRenderer: ShapeRenderer) {
        shapeRenderer.color = ENEMY_COLOR
        shapeRenderer.rect(x, y, width, height)
    }

    fun isOutOfScreen(screenHeight: Float): Boolean {
        return y + height < 0f
    }

    fun isHitByBullet(bullet: Bullet): Boolean {
        return bullet.x >= x && bullet.x <= x + width && bullet.y >= y && bullet.y <= y + height
    }
}