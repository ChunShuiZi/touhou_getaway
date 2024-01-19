package org.thcg.util

import com.badlogic.gdx.Gdx
import org.thcg.GameCore

/**
 * @author Severle
 * @date 2024-01-19 15:40:07
 * @description
 */


val game: GameCore
    get() = Gdx.app.applicationListener as GameCore