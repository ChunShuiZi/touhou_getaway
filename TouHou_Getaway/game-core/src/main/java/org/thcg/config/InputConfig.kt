package org.thcg.config

import com.badlogic.gdx.Input

/**
 * @author Severle
 * @date 2024-01-19 14:31:02
 * @description
 */
data class InputConfig(
    var upKey: Int = Input.Keys.UP,
    var downKey: Int = Input.Keys.DOWN,
    var leftKey: Int = Input.Keys.LEFT,
    var rightKey: Int = Input.Keys.RIGHT,
    var shotKey: Int = Input.Keys.Z,
    var slowKey: Int = Input.Keys.SHIFT_LEFT,
)