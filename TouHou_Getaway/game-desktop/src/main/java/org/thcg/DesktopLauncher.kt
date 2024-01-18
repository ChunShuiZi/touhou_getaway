package org.thcg

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import org.thcg.util.DesktopConfigConstant.DEFAULT_TITLE

/**
 * @author Severle
 * @date 2024-01-18 15:43:43
 * @description
 */
fun main() {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle(DEFAULT_TITLE)
    Lwjgl3Application(GameCore(), config)
}