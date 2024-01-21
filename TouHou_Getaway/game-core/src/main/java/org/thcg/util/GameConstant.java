package org.thcg.util;

/**
 * @author Severle
 * @date 2024-01-19 14:00:37
 * @description
 */
public interface GameConstant {
    int UP = 0x00;
    int DOWN = 0x01;
    int LEFT = 0x02;
    int RIGHT = 0x03;
    int SHOT= 0x04;
    int MOVE_STEP = 4;
    // 物体大小
    int RADIUS = 8;
    // 顶部距离屏幕最上方的不可进入的空余空间
    int TOP_BLANK_HEIGHT = 0;
    int SCORE = 1;
    String USER_FILE_PATH = "game.assets.userdata";
    String GAME_MODE_EASY = "Easy";
    String GAME_MODE_NORMAL = "Normal";
    String GAME_MODE_HARD = "Hard";
    String GAME_MODE_EXPERT = "Expert";
    String[] GAME_MODE = {
            GAME_MODE_EASY,
            GAME_MODE_NORMAL,
            GAME_MODE_HARD,
            GAME_MODE_EXPERT
    };
    String DEFAULT_USER_NAME = "Player";
}
