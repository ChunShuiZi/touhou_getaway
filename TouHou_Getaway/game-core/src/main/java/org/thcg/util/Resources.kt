package org.thcg.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.msgpack.jackson.dataformat.MessagePackMapper
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import javax.annotation.Nonnull

/**
 * @author Severle
 * @date 2024-01-19 14:34:13
 * @description
 */

object Resources {
    private val PATH: String = System.getProperty("game.assets")
    private val log: Logger = LogManager.getLogger(Resources::class.java)
    fun getResource(name: String): URL? {
        return Resources::class.java.classLoader.getResource(name)
    }

    fun getResourceAsStream(name: String): InputStream? {
        return Resources::class.java.classLoader.getResourceAsStream(name)
    }

    fun getResourceFromAssets(name: String): File {
        return File(PATH, name)
    }

    @Throws(Exception::class)
    fun serialize(
        @Nonnull os: OutputStream,
        @Nonnull data: CanSerializable
    ) {
        data.type().getSerializer().serialize(os, data)
    }

    fun <T : CanSerializable> deserialize(
        @Nonnull `is`: InputStream,
        @Nonnull clazz: Class<T>
    ): T {
        try {
            val method = clazz.getDeclaredMethod("type")
            val constructor = clazz.getDeclaredConstructor()
            constructor.isAccessible = true
            val t = constructor.newInstance()
            val type = method.invoke(t) as SerializeType
            return type.getSerializer().deserialize(`is`, clazz)
        } catch (e: Exception) {
            if (log.isErrorEnabled) {
                log.error("Exception caught when deserialize data, {}", e.localizedMessage, e)
            }
            throw RuntimeException(e)
        }
    }

    internal open class Serializer(protected val mapper: ObjectMapper) {
        init {
            this.afterInit()
        }
        protected open fun afterInit() {  }
        protected open fun beforeSerialize() {  }
        protected open fun afterSerialize() {  }
        protected open fun beforeDeserialize() {  }
        protected open fun afterDeserialize() {  }
        fun serialize(
            @Nonnull os: OutputStream,
            @Nonnull data: Any
        ) {
            beforeSerialize()
            mapper.writeValue(os, data)
            afterSerialize()
        }

        @kotlin.jvm.Throws(Exception::class)
        fun <T> deserialize(
            @Nonnull `is`: InputStream,
            @Nonnull clazz: Class<T>
        ): T {
            beforeDeserialize()
            val t = mapper.readValue(`is`, clazz)
            afterDeserialize()
            return t
        }
    }

    enum class SerializeType {
        JSON {
            override fun getSerializer(): Serializer {
                return json
            }
        },
        XML {
            override fun getSerializer(): Serializer {
                return xml
            }
        },
        YAML {
            override fun getSerializer(): Serializer {
                return yaml
            }
        },
        PROPERTIES {
            override fun getSerializer(): Serializer {
                return properties
            }
        },
        MSG {
            override fun getSerializer(): Serializer {
                return msg
            }
        };

        internal abstract fun getSerializer(): Serializer

        companion object {
            private val json = Serializer(JsonMapper())
            private val xml = Serializer(XmlMapper())
            private val yaml = Serializer(YAMLMapper())
            private val properties = Serializer(JavaPropsMapper())
            private val msg = Serializer(MessagePackMapper())
        }
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