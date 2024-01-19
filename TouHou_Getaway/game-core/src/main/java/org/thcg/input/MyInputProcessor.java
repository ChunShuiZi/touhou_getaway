package org.thcg.input;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.InputProcessor;
import org.thcg.GameCore;
import org.thcg.util.GameConstant;

public class MyInputProcessor implements InputProcessor, GameConstant {
    private final GameCore game;

    public MyInputProcessor(GameCore game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        // 处理键盘按下事件
        if(keycode == Input.Keys.W) {
            return game.handleFeedbackData(UP_DOWN);
        }
        if(keycode == Input.Keys.S) {
            return game.handleFeedbackData(DOWN_DOWN);
        }
        if(keycode == Input.Keys.A) {
            return game.handleFeedbackData(LEFT_DOWN);
        }
        if(keycode == Input.Keys.D) {
            return game.handleFeedbackData(RIGHT_DOWN);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W) {
            return game.handleFeedbackData(UP_RELEASE);
        }
        if(keycode == Input.Keys.S) {
            return game.handleFeedbackData(DOWN_RELEASE);
        }
        if(keycode == Input.Keys.A) {
            return game.handleFeedbackData(LEFT_RELEASE);
        }
        if(keycode == Input.Keys.D) {
            return game.handleFeedbackData(RIGHT_RELEASE);
        }
        // 处理键盘抬起事件
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // 处理键盘字符输入事件
        return false;
    }

    // 其他的InputProcessor接口的方法，您可以根据需要来实现

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("111");
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    // ...
}
