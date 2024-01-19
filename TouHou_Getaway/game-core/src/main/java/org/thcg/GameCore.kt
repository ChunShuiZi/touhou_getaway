package org.thcg

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import org.thcg.core.Disposer
import org.thcg.core.Initializer
import org.thcg.input.MyInputProcessor
import org.thcg.input.MyInputProcessor.Type
import org.thcg.util.GameConstant.*


class GameCore : ApplicationAdapter() {
    private lateinit var inputProcessor: MyInputProcessor

    private var x: Int = 300
    private var y: Int = 5
    private var xSpeed: Int = 0
    private var ySpeed: Int = 0
    private lateinit var shape: ShapeRenderer

    override fun create() {
        Initializer.initialize()

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
            x = Gdx.graphics.width
        }
        if (x <= 0) {
            x = 0
        }
        if (y >= Gdx.graphics.width) {
            y = Gdx.graphics.width
        }
        if (y <= 0) {
            y = 0
        }

    }

    fun handleFeedbackData(data: Int) {
        // 处理从输入处理器中传回的数据
        // 示例：打印数据
        if (data == UP or Type.DOWN) {
            ySpeed += MOVE_STEP
        }
        if (data == DOWN or Type.DOWN) {
            ySpeed -= MOVE_STEP
        }
        if (data == LEFT or Type.DOWN) {
            xSpeed -= MOVE_STEP
        }
        if (data == RIGHT or Type.DOWN) {
            xSpeed += MOVE_STEP
        }
        if (data == UP or Type.RELEASE) {
            ySpeed -= MOVE_STEP
        }
        if (data == DOWN or Type.RELEASE) {
            ySpeed += MOVE_STEP
        }
        if (data == LEFT or Type.RELEASE) {
            xSpeed += MOVE_STEP
        }
        if (data == RIGHT or Type.RELEASE) {
            xSpeed -= MOVE_STEP
        }
    }

    override fun dispose() {
        Disposer.dispose()
    }
}

