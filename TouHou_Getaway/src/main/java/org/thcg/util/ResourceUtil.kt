package org.thcg.util

/**
 * @author Severle
 * @date 2024-01-18 00:22:45
 * @description 资源工具
 */
internal interface CloseScope {
    fun <T: AutoCloseable> T.autoClose(): T
}

internal fun autoCloseScope(body: CloseScope.() -> Unit) {
    val resources = mutableListOf<AutoCloseable>()
    val scope = object : CloseScope {
        override fun <T : AutoCloseable> T.autoClose(): T {
            resources.add(this)
            return this
        }
    }
    try {
        scope.body()
    } finally {
        resources.asReversed().forEach {
            it.close()
        }
    }
}