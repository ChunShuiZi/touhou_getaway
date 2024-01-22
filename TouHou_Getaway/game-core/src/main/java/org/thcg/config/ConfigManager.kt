package org.thcg.config

import org.thcg.util.Resources
import org.thcg.util.autoCloseScope
import java.io.BufferedInputStream
import java.io.FileInputStream

/**
 * @author Severle
 * @date 2024-01-19 14:32:30
 * @description
 */
object ConfigManager {
    private lateinit var defaultUserConfig: UserConfig
    init {
        autoCloseScope {
            val conf = Resources.getResourceFromAssets("user.conf")
            if (conf.exists()) {
                val reader = BufferedInputStream(FileInputStream(conf)).autoClose()
                defaultUserConfig = Resources.deserialize(reader, UserConfig::class.java)
            } else
                defaultUserConfig = UserConfig()
        }
    }

    val inputConfig: InputConfig = defaultUserConfig.inputConfig
}