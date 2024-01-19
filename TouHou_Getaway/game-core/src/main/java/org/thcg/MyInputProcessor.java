package org.thcg;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
    private final GameCore game;

    public MyInputProcessor(GameCore game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        // 处理键盘按下事件
        if(keycode == Input.Keys.W) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("1");
        }
        if(keycode == Input.Keys.S) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("2");
        }
        if(keycode == Input.Keys.A) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("3");
        }
        if(keycode == Input.Keys.D) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("4");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("5");
        }
        if(keycode == Input.Keys.S) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("6");
        }
        if(keycode == Input.Keys.A) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("7");
        }
        if(keycode == Input.Keys.D) {
            // 在按下E键时执行的逻辑
            // 对游戏内容产生反馈
            // 示例：打印反馈信息
            game.handleFeedbackData("8");
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
