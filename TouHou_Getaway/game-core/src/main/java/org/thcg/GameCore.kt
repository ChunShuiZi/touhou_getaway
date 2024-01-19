package org.thcg

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import org.thcg.input.MyInputProcessor
import org.thcg.util.GameConstant.*


class GameCore : ApplicationAdapter() {
    private lateinit var inputProcessor: MyInputProcessor

    private var x: Int = 300
    private var y: Int = 5
    private var xSpeed: Int = 0
    private var ySpeed: Int = 0
    private lateinit var shape: ShapeRenderer

    override fun create() {
        shape = ShapeRenderer()
        inputProcessor = MyInputProcessor(this)
        Gdx.input.inputProcessor = inputProcessor
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.circle(x.toFloat(), y.toFloat(), 10f)
        shape.end()
        x += xSpeed
        y += ySpeed

        if (x >= Gdx.graphics.width) {
            xSpeed = 0
        }
        if (x <= 0) {
            xSpeed = 0
        }
        if (y >= Gdx.graphics.width) {
            ySpeed = 0
        }
        if (y <= 0) {
            ySpeed = 0
        }

    }

    fun handleFeedbackData(data: Int): Boolean {
        // 处理从输入处理器中传回的数据
        // 示例：打印数据
        if (data == UP_DOWN) {
            ySpeed += MOVE_STEP
            return true
        }
        if (data == DOWN_DOWN) {
            ySpeed -= MOVE_STEP
            return true
        }
        if (data == LEFT_DOWN) {
            xSpeed -= MOVE_STEP
            return true
        }
        if (data == RIGHT_DOWN) {
            xSpeed += MOVE_STEP
            return true
        }
        if (data == UP_RELEASE && y > 0 && y < Gdx.graphics.width) {
            ySpeed -= MOVE_STEP
            return true
        }
        if (data == DOWN_RELEASE && y > 0 && y < Gdx.graphics.width) {
            ySpeed += MOVE_STEP
            return true
        }
        if (data == LEFT_RELEASE && x > 0 && x < Gdx.graphics.width) {
            xSpeed += MOVE_STEP
            return true
        }
        if (data == RIGHT_RELEASE && x > 0 && x < Gdx.graphics.width) {
            xSpeed -= MOVE_STEP
            return true
        }
        return false
    }

}

