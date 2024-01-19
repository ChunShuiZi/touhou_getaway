package org.thcg

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer


class GameCore : ApplicationAdapter() {
    private var inputProcessor: MyInputProcessor? = null

    var x: Int = 300
    var y: Int = 5
    var xSpeed: Int = 0
    var ySpeed: Int = 0
    var shape: ShapeRenderer? = null

    override fun create() {
        shape = ShapeRenderer()
        inputProcessor = MyInputProcessor(this)
        Gdx.input.setInputProcessor(inputProcessor);
    }
    override fun render() {
        Gdx.input.setInputProcessor(inputProcessor);



        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape!!.begin(ShapeRenderer.ShapeType.Filled)
        shape!!.circle(x.toFloat(), y.toFloat(), 10f)
        shape!!.end()
        x += xSpeed;
        y += ySpeed;

        if (x >= Gdx.graphics.getWidth()) {
            xSpeed = 0;
        }
        if (x <= 0) {
            xSpeed = 0;
        }
        if (y >= Gdx.graphics.getWidth()) {
            ySpeed = 0;
        }
        if (y <= 0) {
            ySpeed = 0;
        }

    }
    fun handleFeedbackData(data: String) {
        // 处理从输入处理器中传回的数据
        // 示例：打印数据
        var result = Integer.parseInt("$data");
        if(result==1&&(y>=0 &&y <= Gdx.graphics.getWidth())) ySpeed=ySpeed+5;
        if(result==2&&(y>=0 &&y <= Gdx.graphics.getWidth())) ySpeed=ySpeed-5;
        if(result==3&&(x>=0 &&x <= Gdx.graphics.getWidth())) xSpeed=xSpeed-5;
        if(result==4&&(x>=0 &&x <= Gdx.graphics.getWidth())) xSpeed=xSpeed+5;
        if(result==5&&(y>0 &&y < Gdx.graphics.getWidth())) ySpeed=ySpeed-5;
        if(result==6&&(y>0 &&y < Gdx.graphics.getWidth())) ySpeed=ySpeed+5;
        if(result==7&&(x>0 &&x < Gdx.graphics.getWidth())) xSpeed=xSpeed+5;
        if(result==8&&(x>0 &&x < Gdx.graphics.getWidth())) xSpeed=xSpeed-5;

    }

}

