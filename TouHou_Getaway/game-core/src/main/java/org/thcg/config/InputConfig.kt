package org.thcg.config

import com.badlogic.gdx.Input

/**
 * @author Severle
 * @date 2024-01-19 14:31:02
 * @description
 */
data class InputConfig(
    var upKey: Int = Input.Keys.W,
    var downKey: Int = Input.Keys.S,
    var leftKey: Int = Input.Keys.A,
    var rightKey: Int = Input.Keys.D
)