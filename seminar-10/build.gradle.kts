plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.edu.jvm.languages"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.7")
    implementation("io.ktor:ktor-server-netty:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

// Add a run task to execute the main class
tasks.register<JavaExec>("run") {
    group = "application"
    mainClass.set("com.jetbrains.educational.ml.WorldMapAppKt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}
