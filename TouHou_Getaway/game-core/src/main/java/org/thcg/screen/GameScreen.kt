package org.thcg.screen
import com.badlogic.gdx.utils.Array

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ScreenViewport
import de.eskalon.commons.screen.ManagedScreenAdapter
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.thcg.input.MyInputProcessor
import org.thcg.input.MyInputProcessor.Type
import org.thcg.util.GameConstant.*


/**
 * @author Severle
 * @date 2024-01-19 15:15:08
 * @description
 */
class GameScreen : ManagedScreenAdapter() {
    private companion object {
        private val log: Logger = LogManager.getLogger(GameScreen::class.java)// 创建Logger对象，用于日志记录

    }

    private var inputProcessor: MyInputProcessor = MyInputProcessor(this)// 创建MyInputProcessor对象，并传入当前实例
    private var bullets: MutableList<Bullet> = mutableListOf()  // 用于存储子弹对象的列表
    private var shot: Int = 0
    // 初始化人物位置
    private var x: Int = 300// 人物横坐标
    private var y: Int = 8// 人物纵坐标
    private var xSpeed: Int = 0// 人物横向速度
    private var ySpeed: Int = 0// 人物纵向速度
    private var shape: ShapeRenderer = ShapeRenderer() // 创建ShapeRenderer对象，用于绘制形状
    private var viewport: ScreenViewport = ScreenViewport()// 创建ScreenViewport对象，用于处理屏幕显示区域

    init {
        addInputProcessor(inputProcessor)// 添加输入处理器到当前屏幕
    }

    override fun render(delta: Float) {
        viewport.apply()// 应用视图口设置
        shape.projectionMatrix = viewport.camera.combined // 设置ShapeRenderer的投影矩阵为视图口相机的组合矩阵
        shape.begin(ShapeRenderer.ShapeType.Filled)// 开始填充形状绘制
        shape.circle(x.toFloat(), y.toFloat(), RADIUS.toFloat())// 绘制一个圆形

        val color = Color.RED // 设置绘制颜色为红色
        shape.color = color // 设置ShapeRenderer的绘制颜色
        shape.end()// 结束形状绘制
        x += xSpeed// 更新人物横坐标
        y += ySpeed // 更新人物纵坐标
        if (x >= viewport.worldWidth - RADIUS)
            x = viewport.worldWidth.toInt() - RADIUS
        if (y >= viewport.worldHeight - RADIUS - TOP_BLANK_HEIGHT)
            y = viewport.worldHeight.toInt() - RADIUS - TOP_BLANK_HEIGHT
        if (x <= RADIUS)
            x = RADIUS
        if (y <= RADIUS)
            y = RADIUS
if(shot==1) {
    bullets.add(Bullet(x, y, 5, 1))
}
    // 移除已过期的子弹并更新和绘制子弹
    bullets.removeAll { bullet -> bullet.isExpired() }
    shape.begin(ShapeRenderer.ShapeType.Filled)
    for (bullet in bullets) {
        val color2 = Color.WHITE // 设置绘制颜色为红色
        shape.color = color2
        bullet.update(delta)
        bullet.draw(shape)
        shape.color = color
    }

        shape.end()
    }

    fun handleFeedbackData(data: Int) {
        when (data) {
            UP or Type.DOWN -> {
                ySpeed += MOVE_STEP
            }

            DOWN or Type.DOWN -> {
                ySpeed -= MOVE_STEP
            }

            LEFT or Type.DOWN -> {
                xSpeed -= MOVE_STEP
            }

            RIGHT or Type.DOWN -> {
                xSpeed += MOVE_STEP
            }

            UP or Type.RELEASE -> {
                ySpeed -= MOVE_STEP
            }

            DOWN or Type.RELEASE -> {
                ySpeed += MOVE_STEP
            }

            LEFT or Type.RELEASE -> {
                xSpeed += MOVE_STEP
            }

            RIGHT or Type.RELEASE -> {
                xSpeed -= MOVE_STEP
            }
            SHOT or Type.DOWN -> {
                shot=1

            }

            SHOT or Type.RELEASE -> {
shot=0

            }
        }
    }

    fun debugLocation() {
        if (log.isDebugEnabled) {
            log.debug("Now Location: ({}, {})", x, y)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        shape.dispose()
    }
}
class Bullet(
    private val x: Int,
    private var y: Int,
    private val size: Int,
    private val bulletType: Int
) {
    private var distanceTraveled: Int = 0
    private val maxDistance: Int = 5000

    fun update(delta: Float) {
        val speed: Float = 2000f
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