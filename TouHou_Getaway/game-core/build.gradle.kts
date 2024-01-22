plugins {
    id("java-library")
    kotlin("jvm") version "2.0.0-Beta2"
}

group = "org.thcg"
version = "0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenLocal()
    maven { setUrl("https://maven.aliyun.com/repository/public/") }
    maven { setUrl("https://maven.aliyun.com/repository/spring/") }
    mavenCentral()
    google()
    gradlePluginPortal()
    maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { setUrl("https://oss.sonatype.org/content/repositories/releases/") }
    maven { setUrl("https://jitpack.io") }
}

sourceSets {
    main {
        java {
            srcDirs(listOf("src/main/java"))
        }
        resources {
            srcDirs(listOf("src/main/resources"))
        }
    }
}

val appName: String = project.property("app.name").toString()
val projectName: String = "$appName-core"

val gdxVersion: String = project.property("gdx.version").toString()
val ktxVersion: String = project.property("ktx.version").toString()
val roboVMVersion: String = project.property("roboVM.version").toString()
val gdxControllersVersion: String = project.property("gdx.controllers.version").toString()
val box2dLightsVersion: String = project.property("box2d.lights.version").toString()
val ashleyVersion: String = project.property("ashley.version").toString()
val aiVersion: String = project.property("ai.version").toString()

val tuningForkVersion = "4.2.0"
val screenManagerVersion = "0.7.0"
val shapeDrawerVersion = "2.5.0"
val pieMenuVersion = "5.0.0"
val textraTypistVersion = "0.10.0"
val typingLabelVersion = "1.3.0"

val jGraphTVersion = "1.5.2"

val log4jVersion = "3.0.0-beta1"
val lwjglVersion = "3.3.3"
val jacksonVersion = "2.16.1"
val msgPackVersion = "0.9.7"
val configVersion = "1.4.3"
val config4kVersion = "0.6.0"
val kotlinVersion = "2.0.0-Beta2"
val kotlinxCoroutinesVersion = "1.8.0-RC2"
val lombokVersion = "1.18.30"

val lwjglNatives = Pair(
    System.getProperty("os.name")!!,
    System.getProperty("os.arch")!!
).let { (name, arch) ->
    when {
        arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else if (arch.startsWith("ppc"))
                "natives-linux-ppc64le"
            else if (arch.startsWith("riscv"))
                "natives-linux-riscv64"
            else
                "natives-linux"
        arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) }     ->
            "natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"
        arrayOf("Windows").any { name.startsWith(it) }                ->
            if (arch.contains("64"))
                "natives-windows${if (arch.startsWith("aarch64")) "-arm64" else ""}"
            else
                "natives-windows-x86"
        else                                                                            ->
            throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

dependencies {
    api("org.apache.logging.log4j:log4j-core:$log4jVersion")
    api("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jVersion")

    api("org.msgpack:msgpack-core:$msgPackVersion")

    api("com.fasterxml.jackson:jackson-bom:$jacksonVersion")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-properties:$jacksonVersion")
    api("org.msgpack:jackson-dataformat-msgpack:$msgPackVersion")

    api("com.typesafe:config:$configVersion")
    api("io.github.config4k:config4k:$config4kVersion")

    api("com.badlogicgames.gdx:gdx:$gdxVersion")
    api("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")
    api("com.badlogicgames.gdx:gdx-box2d:$gdxVersion")
    api("com.badlogicgames.gdx-controllers:gdx-controllers-core:$gdxControllersVersion")
    api("com.badlogicgames.box2dlights:box2dlights:$box2dLightsVersion")
    api("com.badlogicgames.ashley:ashley:$ashleyVersion")
    api("com.badlogicgames.gdx:gdx-ai:$aiVersion")

    api("com.github.Hangman:TuningFork:$tuningForkVersion")
    api("com.github.crykn:libgdx-screenmanager:$screenManagerVersion")
    api("space.earlygrey:shapedrawer:$shapeDrawerVersion")
    api("com.github.payne911:PieMenu:$pieMenuVersion")
    api("com.github.tommyettinger:textratypist:$textraTypistVersion")
    api("com.rafaskoberg.gdx:typing-label:$typingLabelVersion")

    api("org.jgrapht:jgrapht-core:$jGraphTVersion")

    api("io.github.libktx:ktx-actors:$ktxVersion")
    api("io.github.libktx:ktx-ai:$ktxVersion")
    api("io.github.libktx:ktx-app:$ktxVersion")
    api("io.github.libktx:ktx-ashley:$ktxVersion")
    api("io.github.libktx:ktx-assets-async:$ktxVersion")
    api("io.github.libktx:ktx-assets:$ktxVersion")
    api("io.github.libktx:ktx-async:$ktxVersion")
    api("io.github.libktx:ktx-box2d:$ktxVersion")
    api("io.github.libktx:ktx-collections:$ktxVersion")
    api("io.github.libktx:ktx-freetype-async:$ktxVersion")
    api("io.github.libktx:ktx-freetype:$ktxVersion")
    api("io.github.libktx:ktx-graphics:$ktxVersion")
    api("io.github.libktx:ktx-i18n:$ktxVersion")
    api("io.github.libktx:ktx-inject:$ktxVersion")
    api("io.github.libktx:ktx-json:$ktxVersion")
    api("io.github.libktx:ktx-log:$ktxVersion")
    api("io.github.libktx:ktx-math:$ktxVersion")
    api("io.github.libktx:ktx-preferences:$ktxVersion")
    api("io.github.libktx:ktx-reflect:$ktxVersion")
    api("io.github.libktx:ktx-scene2d:$ktxVersion")
    api("io.github.libktx:ktx-style:$ktxVersion")
    api("io.github.libktx:ktx-tiled:$ktxVersion")
    api("io.github.libktx:ktx-vis-style:$ktxVersion")
    api("io.github.libktx:ktx-vis:$ktxVersion")

    api(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    api("org.lwjgl", "lwjgl")
    api("org.lwjgl", "lwjgl-lz4")
    api("org.lwjgl", "lwjgl-xxhash")
    api("org.lwjgl", "lwjgl-zstd")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lz4", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-xxhash", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-zstd", classifier = lwjglNatives)

    api(platform("org.jetbrains.kotlin:kotlin-bom"))
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.withType<Copy>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}