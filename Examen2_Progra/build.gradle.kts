// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    // Compose Compiler: desde Kotlin 2.0 viene como plugin del propio Kotlin
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
    // KSP: procesador de anotaciones (genera el codigo de Room)
    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false
}
