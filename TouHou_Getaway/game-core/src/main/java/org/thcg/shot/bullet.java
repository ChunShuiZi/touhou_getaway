package org.thcg.shot;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class bullet {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    public bullet(int x, int y, int size ,int bulletType    ) {
        this.x = x;
        this.y = y;
        this.size = size;
        //自机直线子弹
        if(bulletType==1)
        {
            this.xSpeed = 0;
            this.ySpeed = 30;
        }
        //诱导子弹
        if(bulletType==2)
        {

        }
    }
    public void update() {
        x += xSpeed;
        y += ySpeed;
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
    }
}