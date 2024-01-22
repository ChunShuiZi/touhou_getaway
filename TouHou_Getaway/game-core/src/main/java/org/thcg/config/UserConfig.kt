package org.thcg.config

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.thcg.util.CanSerializable
import org.thcg.util.Resources

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

    override fun type(): Resources.SerializeType {
        return Resources.SerializeType.JSON
    }
}
