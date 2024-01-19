package org.thcg.core

import java.io.File

/**
 * @author Severle
 * @date 2024-01-19 14:41:30
 * @description
 */
object Initializer {
    fun initialize() {
        System.setProperty("game.assets", File("assets").absolutePath)
    }
}