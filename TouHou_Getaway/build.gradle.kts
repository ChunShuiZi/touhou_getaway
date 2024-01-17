plugins {
    id("java-library")
    kotlin("jvm") version "2.0.0-Beta2"
}

group = "org.thcg"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

val log4jVersion = "3.0.0-beta1"
val gsonVersion = "2.10.1"
val xmlVersion = "4.0.1"
val migLayoutVersion = "11.2"
val flatlafVersion = "3.2.5"
val flatlafJetbrainsMonoVersion = "2.242"
val kotlinVersion = "2.0.0-Beta2"
val kotlinCoroutinesVersion = "1.8.0-RC2"
val lombokVersion = "1.18.30"
val junitVersion = "5.9.2"
val kotlinTestVersion = "1.8.10"

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jVersion")

    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:$xmlVersion")

    implementation("com.miglayout:miglayout-swing:$migLayoutVersion")
    implementation("com.formdev:flatlaf:$flatlafVersion")
    implementation("com.formdev:flatlaf-intellij-themes:$flatlafVersion")
    implementation("com.formdev:flatlaf-extras:$flatlafVersion")
    implementation("com.formdev:flatlaf-fonts-jetbrains-mono:$flatlafJetbrainsMonoVersion")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jvm:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$kotlinCoroutinesVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinTestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runApplication") {
    systemProperty("log4j.skipJansi", "false")
    mainClass = "org.thcg.Main"
    classpath = sourceSets["main"].runtimeClasspath
}
