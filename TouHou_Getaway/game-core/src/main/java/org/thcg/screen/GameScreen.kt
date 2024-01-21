package org.thcg.screen

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
        private val log: Logger = LogManager.getLogger(GameScreen::class.java)
    }

    private var inputProcessor: MyInputProcessor = MyInputProcessor(this)

    // 初始化人物位置
    private var x: Int = 300
    private var y: Int = 8
    private var xSpeed: Int = 0
    private var ySpeed: Int = 0
    private var shape: ShapeRenderer = ShapeRenderer()
    private var viewport: ScreenViewport = ScreenViewport()

    private var shotting : Int =0
    init {
        addInputProcessor(inputProcessor)
    }

    override fun render(delta: Float) {
        viewport.apply()
        shape.projectionMatrix = viewport.camera.combined
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.circle(x.toFloat(), y.toFloat(), RADIUS.toFloat())

        if(shotting==1)
        {
            print("0")
        }

        val color = Color.RED
        shape.color = color
        shape.end()
        x += xSpeed
        y += ySpeed
        if (x >= viewport.worldWidth - RADIUS)
            x = viewport.worldWidth.toInt() - RADIUS
        if (y >= viewport.worldHeight - RADIUS - TOP_BLANK_HEIGHT)
            y = viewport.worldHeight.toInt() - RADIUS - TOP_BLANK_HEIGHT
        if (x <= RADIUS)
            x = RADIUS
        if (y <= RADIUS)
            y = RADIUS
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
                shotting=1
            }

            SHOT or Type.RELEASE -> {
                shotting=0
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