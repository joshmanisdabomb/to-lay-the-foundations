pluginManagement {
    repositories {
        maven(url = "https://maven.fabricmc.net/")
        maven(url = "https://maven.architectury.dev/")
        maven(url = "https://maven.minecraftforge.net/")
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version extra["kotlin_version"].toString()
        id("architectury-plugin") version extra["architectury_plugin_version"].toString()
        id("dev.architectury.loom") version extra["architectury_loom_version"].toString()
        id("com.github.johnrengelman.shadow") version "7.1.2"
    }
}

include("common")
include("fabric-like")
include("fabric")
include("quilt")
include("forge")

rootProject.name = extra["mod_id"].toString()
