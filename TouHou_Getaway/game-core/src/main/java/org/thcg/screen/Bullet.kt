package org.thcg.screen

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import kotlin.math.cos
import kotlin.math.sin

// 定义子弹类
open class Bullet(
    var x: Int,
    var y: Int,
    private val size: Int,
    private val bulletType: Int = 1
) {
    private var distanceTraveled: Int = 0
    private val maxDistance: Int = 5000
    private val obliqueRight: Int = 10
    private val obliqueLeft: Int = 100

    open fun userBulletUpdate(delta: Float) {
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

    open fun draw(shape: ShapeRenderer) {
        shape.circle(x.toFloat(), y.toFloat(), size.toFloat())
    }

    fun isExpired(): Boolean {
        return distanceTraveled >= maxDistance
    }
}

// 环状子弹逻辑
internal class RingBullet(
    private val startX: Int, private val startY: Int, bulletSpeed: Int, // 环状子弹的半径
    var radius: Int
) : Bullet(startX, startY, bulletSpeed) {
    private var angle = 0.0

    override fun userBulletUpdate(delta: Float) {
        angle += delta * 50 // 设置旋转速度
        x = (radius * cos(Math.toRadians(angle))).toInt() + startX
        y = (radius * sin(Math.toRadians(angle))).toInt() + startY
    }
}

// 诱导子弹逻辑
internal class HomingBullet(
    startX: Int, startY: Int, bulletSpeed: Int,
    private var playerX: Int,
    private var playerY: Int
) : Bullet(startX, startY, bulletSpeed) {
    private val speed = 2 // 设置诱导速度

    override fun userBulletUpdate(delta: Float) {
        if (playerX > x) {
            x += speed
        } else if (playerX < x) {
            x -= speed
        }

        if (playerY > y) {
            y += speed
        } else if (playerY < y) {
            y -= speed
        }
    }
}
