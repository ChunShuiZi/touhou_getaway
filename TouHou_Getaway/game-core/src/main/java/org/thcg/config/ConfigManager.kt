package org.thcg.config

import org.thcg.util.Resources
import org.thcg.util.autoCloseScope
import java.io.BufferedReader
import java.io.FileReader

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
                val reader = BufferedReader(FileReader(conf)).autoClose()
                defaultUserConfig = Resources.GSON.fromJson(reader, UserConfig::class.java)
            } else
                defaultUserConfig = UserConfig()
        }
    }

    val inputConfig: InputConfig = defaultUserConfig.inputConfig
}