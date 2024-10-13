pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            content {
                includeModule("fabric-loom", "fabric-loom.gradle.plugin")
                includeGroup("net.fabricmc.unpick")
                includeGroup("net.fabricmc")
            }
        }

        gradlePluginPortal()
    }
}
