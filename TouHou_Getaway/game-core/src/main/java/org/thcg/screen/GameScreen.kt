package org.thcg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import de.eskalon.commons.screen.ManagedScreenAdapter
import org.thcg.input.MyInputProcessor
import org.thcg.util.GameConstant

/**
 * @author Severle
 * @date 2024-01-19 15:15:08
 * @description
 */
class GameScreen : ManagedScreenAdapter() {
    private var inputProcessor: MyInputProcessor = MyInputProcessor(this)

    //初始化人物位置
    private var x: Int = 300
    private var y: Int = 8
    private var xSpeed: Int = 0
    private var ySpeed: Int = 0
    private var shape: ShapeRenderer = ShapeRenderer()
    init {
        addInputProcessor(inputProcessor)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.circle(x.toFloat(), y.toFloat(), 8f)
        val color = Color.RED
        shape.color=color
        shape.end()
        x += xSpeed
        y += ySpeed
        //边界条件，使人物一直处在屏幕内
        if (x >= Gdx.graphics.width - 8) {
            x = Gdx.graphics.width - 8
        }
        if (x <= 8) {
            x = 8
        }
        if (y >= Gdx.graphics.width - 168) {
            y = Gdx.graphics.width - 168
        }
        if (y <= 8) {
            y = 8
        }
    }

    fun handleFeedbackData(data: Int) {
        // 处理从输入处理器中传回的数据
        // 示例：打印数据
        if (data == GameConstant.UP or MyInputProcessor.Type.DOWN) {
            ySpeed += GameConstant.MOVE_STEP
        }
        if (data == GameConstant.DOWN or MyInputProcessor.Type.DOWN) {
            ySpeed -= GameConstant.MOVE_STEP
        }
        if (data == GameConstant.LEFT or MyInputProcessor.Type.DOWN) {
            xSpeed -= GameConstant.MOVE_STEP
        }
        if (data == GameConstant.RIGHT or MyInputProcessor.Type.DOWN) {
            xSpeed += GameConstant.MOVE_STEP
        }
        if (data == GameConstant.UP or MyInputProcessor.Type.RELEASE) {
            ySpeed -= GameConstant.MOVE_STEP
        }
        if (data == GameConstant.DOWN or MyInputProcessor.Type.RELEASE) {
            ySpeed += GameConstant.MOVE_STEP
        }
        if (data == GameConstant.LEFT or MyInputProcessor.Type.RELEASE) {
            xSpeed += GameConstant.MOVE_STEP
        }
        if (data == GameConstant.RIGHT or MyInputProcessor.Type.RELEASE) {
            xSpeed -= GameConstant.MOVE_STEP
        }
    }

    override fun dispose() {
        shape.dispose()
    }
}