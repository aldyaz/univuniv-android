// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.parcelize) apply false
    alias(libs.plugins.ksp) apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
