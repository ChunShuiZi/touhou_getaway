package org.thcg.screen

import kotlin.math.cos
import kotlin.math.sin

// 定义子弹类
internal class BulletLogic {
    var x: Int = 0 // 子弹的x坐标
    var y: Int = 0 // 子弹的y坐标
    var speed: Int = 0 // 子弹的速度

    // 构造函数
    fun Bullet(startX: Int, startY: Int, bulletSpeed: Int) {
        x = startX
        y = startY
        speed = bulletSpeed
    }

    // 子弹移动方法
    fun move() {
        y += speed // 子弹向下移动
    }
}

// 直线子弹逻辑
internal class StraightBullet(startX: Int, startY: Int, bulletSpeed: Int) : Bullet(startX, startY, bulletSpeed)

// 诱导子弹逻辑
internal class HomingBullet(
    startX: Int, startY: Int, bulletSpeed: Int, // 玩家的x坐标
    var playerX: Int, // 玩家的y坐标
    var playerY: Int
) : Bullet(startX, startY, bulletSpeed) {
    // 诱导子弹移动方法，让子弹向玩家位置移动
    fun move() {
        if (playerX > x) {
            x++
        } else if (playerX < x) {
            x--
        }

        if (playerY > y) {
            y++
        } else if (playerY < y) {
            y--
        }
    }
}

// 环状子弹逻辑
internal class RingBullet(
    private val startX: Int, private val startY: Int, bulletSpeed: Int, // 环状子弹的半径
    var radius: Int
) : Bullet(startX, startY, bulletSpeed) {
    // 环状子弹移动方法
    fun move() {
        // 根据半径绕圆心转动
        x = (radius * cos(Math.toRadians(y.toDouble()))).toInt() + startX
        y = (radius * sin(Math.toRadians(y.toDouble()))).toInt() + startY
    }
}


