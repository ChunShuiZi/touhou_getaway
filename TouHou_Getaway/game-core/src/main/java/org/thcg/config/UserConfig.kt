package org.thcg.config

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.thcg.util.CanSerializable
import org.thcg.util.Resources
import java.io.OutputStream

/**
 * @author Severle
 * @date 2024-01-19 14:30:41
 * @description
 */
data class UserConfig(
    var inputConfig: InputConfig = InputConfig()
) : CanSerializable {
    companion object {
        private val log: Logger = LogManager.getLogger(UserConfig::class.java)
    }
    override fun serialize(os: OutputStream): Boolean {
        try {
            Resources.serialize(os, this)
            return true
        } catch (e: Exception) {
            if (log.isWarnEnabled) {
                log.warn("Exception caught when serialize config, {}", e.localizedMessage, e)
            }
            return false
        }
    }

    override fun type(): Resources.SerializeType {
        return Resources.SerializeType.JSON
    }
}
