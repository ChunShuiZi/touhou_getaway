package org.thcg.input;

import com.badlogic.gdx.InputProcessor;
import org.thcg.GameCore;
import org.thcg.config.ConfigManager;
import org.thcg.config.InputConfig;
import org.thcg.util.GameConstant;

public class MyInputProcessor implements InputProcessor, GameConstant {
    private final GameCore game;

    public MyInputProcessor(GameCore game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return key(keycode, Type.DOWN);
    }

    @Override
    public boolean keyUp(int keycode) {
        return key(keycode, Type.RELEASE);
    }

    private boolean key(int keycode, int type) {
        InputConfig inputConfig = ConfigManager.INSTANCE.getInputConfig();
        if(keycode == inputConfig.getUpKey()) {
            game.handleFeedbackData(UP | type);
            return true;
        }
        if(keycode == inputConfig.getDownKey()) {
            game.handleFeedbackData(DOWN | type);
            return true;
        }
        if(keycode == inputConfig.getLeftKey()) {
            game.handleFeedbackData(LEFT | type);
            return true;
        }
        if(keycode == inputConfig.getRightKey()) {
            game.handleFeedbackData(RIGHT | type);
            return true;
        }
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

    public interface Type {
        int DOWN = 0x00;
        int RELEASE = 0x10;
    }
}