plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    id("com.google.devtools.ksp") version "2.0.20-1.0.24"
}

group = "hu.bme.onlabor"
version = "1.0.0"
application {
    mainClass.set("hu.bme.onlabor.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "build/generated/ksp/main/kotlin")
    }
}

dependencies {
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.postgresql)
    implementation(libs.hikari)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.utils)
    implementation(libs.ktor.server.cors)

    implementation(platform("org.kotlincrypto.hash:bom:0.5.3"))
    implementation("org.kotlincrypto.hash:md")
    implementation("io.ktor:ktor-server-auth:2.3.12")
    implementation("com.auth0:java-jwt:4.4.0")


    implementation(libs.ktor.swagger.ui)

    implementation(libs.kotlinpoet)

    implementation(project(":server:orm_annotation"))
    ksp(project(":server:orm_processor"))

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

