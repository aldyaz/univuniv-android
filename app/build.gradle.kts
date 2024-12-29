plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.parcelize)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version libs.versions.kotlin
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.aldyaz.univuniv"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aldyaz.univuniv"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.bundles.android.base)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.coroutines)
    implementation(libs.kotlinx.serialization)
    implementation(libs.hilt.android)
    implementation(libs.coil)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.uiTooling)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(compose.preview)

    implementation(libs.hilt.navigation)
    implementation(libs.compose.navigation)

    debugImplementation(libs.bundles.pluto)
    releaseImplementation(libs.bundles.pluto.noop)

    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit5)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}