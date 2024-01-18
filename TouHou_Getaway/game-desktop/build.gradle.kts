import org.gradle.jvm.tasks.Jar

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

val mainClassName = "org.thcg.DesktopLauncherKt"
val assetsDir = rootProject.file("assets")

sourceSets {
    main {
        java {
            srcDirs(listOf("src/"))
        }
        resources {
            srcDirs(listOf("../assets"))
        }
    }
}

val appName: String = project.property("app.name").toString()
val projectName: String = "$appName-desktop"
val gdxVersion: String = project.property("gdx.version").toString()
val roboVMVersion: String = project.property("roboVM.version").toString()
val gdxControllersVersion: String = project.property("gdx.controllers.version").toString()

val gameSVCsVersion = "1.1.0"
val gdxDialogsVersion = "1.3.0"
val lombokVersion = "1.18.30"

dependencies {
    implementation(project(":game-core"))
    api("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
    api("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
    api("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop")
    api("com.badlogicgames.gdx-controllers:gdx-controllers-desktop:$gdxControllersVersion")
    api("com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop")
    api("de.golfgl.gdxgamesvcs:gdx-gamesvcs-core-gamejolt:$gameSVCsVersion")
    api("de.tomgrill.gdxdialogs:gdx-dialogs-desktop:$gdxDialogsVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.withType<JavaExec>().configureEach {
    systemProperty("log4j.skipJansi", "false")
}

tasks.withType<Copy>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.register<JavaExec>("run") {
    dependsOn(tasks.named("classes"))
    mainClass = mainClassName
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    workingDir = assetsDir
    isIgnoreExitValue = true

    if (System.getProperty("os.name")!! == "Mac OS X") {
        jvmArgs(listOf(jvmArgs, "-XstartOnFirstThread"))
    }
}

tasks.register<JavaExec>("debug") {
    dependsOn(tasks.named("classes"))
    mainClass = mainClassName
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    workingDir = assetsDir
    isIgnoreExitValue = true
    debug = true
}

tasks.register<Jar>("dist") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = mainClassName
    }
    dependsOn(configurations.runtimeClasspath)
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory)
            it
        else
            zipTree(it)
    })
    with(tasks.jar.get().rootSpec)
}

tasks.named("dist").configure {
    dependsOn(tasks.named("classes"))
}