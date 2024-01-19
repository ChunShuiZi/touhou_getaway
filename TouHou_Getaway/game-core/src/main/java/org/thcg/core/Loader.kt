package org.thcg.core

import com.kotcrab.vis.ui.VisUI
import java.io.File

/**
 * @author Severle
 * @date 2024-01-19 15:35:36
 * @description
 */
object Loader {
    fun initialize() {
        System.setProperty("game.assets", File("assets").absolutePath)
        VisUI.load()
    }

    fun terminal() {
        VisUI.dispose()
    }
}