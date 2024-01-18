package org.thcg

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class GameCore : ApplicationAdapter() {
    var x: Int = 50
    var y: Int = 50
    var xSpeed: Int = 0
    var shape: ShapeRenderer? = null
    override fun create() {
        shape = ShapeRenderer()

    }
    override fun render() {
        x += 5
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape!!.begin(ShapeRenderer.ShapeType.Filled)
        shape!!.circle(x.toFloat(), y.toFloat(), 10f)
        shape!!.end()
        x += xSpeed;
        if (x > Gdx.graphics.getWidth()) {
            xSpeed = -10;
        }
        if (x < 0) {
            xSpeed = 0;
        }
    }

}