@file:Suppress("DEPRECATION")

val ktor_version: String by rootProject.project
val voyager_version: String by rootProject.project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "com.example"
version = "1.0-SNAPSHOT"

kotlin {
    androidTarget()

    jvm("desktop") {
        jvmToolchain(11)
    }

    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                implementation(compose.materialIconsExtended)
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("cafe.adriel.voyager:voyager-navigator:$voyager_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val commonComposeKmpMain by creating {
            dependsOn(commonMain)
        }

        val desktopMain by getting {
            dependencies {
                dependsOn(commonComposeKmpMain)
                api(compose.preview)
                implementation(compose.materialIconsExtended)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")
                // Ktor
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
            }
        }
        val desktopTest by getting

        val androidMain by getting {
            dependencies {
                dependsOn(commonComposeKmpMain)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
                // Ktor
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
            }
        }
        val androidUnitTest by getting

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)

                implementation("io.ktor:ktor-client-js:$ktor_version")

            }
        }
        val jsTest by getting
    }
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
