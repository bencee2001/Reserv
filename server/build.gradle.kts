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

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation("io.insert-koin:koin-ktor:4.0.0")
    implementation("io.insert-koin:koin-logger-slf4j:4.0.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
    implementation("org.jetbrains.exposed:exposed-core:0.54.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.54.0")
    implementation("com.squareup:kotlinpoet:1.16.0")

    implementation(project(":server:orm_annotation"))
    ksp(project(":server:orm_processor"))

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}