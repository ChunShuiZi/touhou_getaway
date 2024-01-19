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
    int MOVE_STEP = 3;
    // 物体大小
    int RADIUS = 8;
    // 顶部距离屏幕最上方的不可进入的空余空间
    int TOP_BLANK_HEIGHT = 0;
    int SCORE = 1;
}
