package org.thcg.shot;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class bullet {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    public bullet(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update() {
        x += xSpeed;
        y += ySpeed;

    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
    }
}