package org.thcg.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.InputStream
import java.net.URL

/**
 * @author Severle
 * @date 2024-01-19 14:34:13
 * @description
 */

object Resources {
    private val PATH: String = System.getProperty("game.assets")
    val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
    fun getResource(name: String): URL? {
        return Resources::class.java.classLoader.getResource(name)
    }

    fun getResourceAsStream(name: String): InputStream? {
        return Resources::class.java.classLoader.getResourceAsStream(name)
    }

    fun getResourceFromAssets(name: String): File {
        return File(PATH, name)
    }
}

internal interface CloseScope {
    fun <T : AutoCloseable> T.autoClose(): T
}

internal fun autoCloseScope(body: CloseScope.() -> Unit) {
    val resources = mutableListOf<AutoCloseable>()
    val closeScope = object : CloseScope {
        override fun <T : AutoCloseable> T.autoClose(): T {
            resources.add(this)
            return this
        }
    }
    try {
        closeScope.body()
    } finally {
        resources.asReversed().forEach {
            it.close()
        }
    }
}