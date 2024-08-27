import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    id("kotlin-android")

fun PluginDependenciesSpec.kotlinKapt(): PluginDependencySpec =
    id("kotlin-kapt")

fun PluginDependenciesSpec.safeArgs(): PluginDependencySpec =
    id("androidx.navigation.safeargs.kotlin")

fun PluginDependenciesSpec.kspPlugin(): PluginDependencySpec =
    id("com.google.devtools.ksp").version("1.7.0-1.0.6")

fun PluginDependenciesSpec.javaLibrary(): PluginDependencySpec =
    id("java-library")

fun PluginDependenciesSpec.kotlinParcelize(): PluginDependencySpec =
    id("kotlin-parcelize")

fun PluginDependenciesSpec.ggservices(): PluginDependencySpec =
    id("com.google.gms.google-services")

fun PluginDependenciesSpec.kotlinAllOpen(): PluginDependencySpec =
    id("kotlin-allopen")

fun PluginDependenciesSpec.firebaseCrashlytics(): PluginDependencySpec =
    id("com.google.firebase.crashlytics")

fun PluginDependenciesSpec.firebasePerf(): PluginDependencySpec =
    id("com.google.firebase.firebase-perf")

fun PluginDependenciesSpec.ktlint(): PluginDependencySpec =
    id("org.jlleitschuh.gradle.ktlint")

fun PluginDependenciesSpec.composeCompiler(): PluginDependencySpec =
    id("org.jetbrains.kotlin.plugin.compose")

fun PluginDependenciesSpec.jetBrainCompose(): PluginDependencySpec =
    id("org.jetbrains.compose")

fun PluginDependenciesSpec.kotlinMultiplatform(): PluginDependencySpec =
    id("org.jetbrains.kotlin.multiplatform")

fun PluginDependenciesSpec.kotlinSerialization(): PluginDependencySpec =
    id("org.jetbrains.kotlin.plugin.serialization")

fun PluginDependenciesSpec.realm(): PluginDependencySpec =
    id("io.realm.kotlin")
