package org.thcg.screen

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Bullet(
    private val x: Int,
    private var y: Int,
    private val size: Int,
    private val bulletType: Int
) {
    private var distanceTraveled: Int = 0
    private val maxDistance: Int = 5000

    fun update(delta: Float) {
        val speed: Float = 1200f
        val oldY: Int = y

        when (bulletType) {
            // 处理不同类型的子弹移动逻辑
            // ...

            else -> {
                y += (speed * delta).toInt()
            }
        }

        distanceTraveled += (y - oldY)
    }

    fun draw(shape: ShapeRenderer) {
        shape.circle(x.toFloat(), y.toFloat(), size.toFloat())
    }

    fun isExpired(): Boolean {
        return distanceTraveled >= maxDistance
    }
}