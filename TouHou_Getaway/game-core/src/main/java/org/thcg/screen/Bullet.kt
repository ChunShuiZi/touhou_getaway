package org.thcg.screen

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Bullet(
    var x: Int,
    var y: Int,
    private val size: Int,
    private val bulletType: Int = 1
) {
    private var distanceTraveled: Int = 0
    private val maxDistance: Int = 5000
    private val obliqueRight: Int = 10
    private val obliqueLeft: Int = 100

    fun userBulletUpdate(delta: Float) {
        val speed: Float = 1200f
        val oldY: Int = y

        when (bulletType) {
            obliqueRight -> {
                x += (speed * delta).toInt() / 10
                y += (speed * delta).toInt()
            }
            obliqueLeft -> {
                x -= (speed * delta).toInt() / 10
                y += (speed * delta).toInt()
            }

            else -> {
                y += (speed * delta).toInt()
            }
        }
        distanceTraveled += (y - oldY)
    }

    fun enemyBulletUpdate(speed: Float){
        val oldY: Int = y

        when (bulletType) {
            obliqueRight -> {
                x += speed.toInt() / 10
                y -= speed.toInt()
            }
            obliqueLeft -> {
                x -= speed.toInt() / 10
                y -= speed.toInt()
            }

            else -> {
                y -= speed.toInt()
            }
        }
        distanceTraveled += (oldY - y)
    }

    fun draw(shape: ShapeRenderer) {
        shape.circle(x.toFloat(), y.toFloat(), size.toFloat())
    }

    fun isExpired(): Boolean {
        return distanceTraveled >= maxDistance
    }
}