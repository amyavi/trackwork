plugins {
    alias(libs.plugins.fabric.loom)
}

group = project.property("group") as String
version = libs.versions.minecraft.get() + "-" + project.property("version") as String

repositories {
    maven("https://mvn.devos.one/releases/")
    maven("https://mvn.devos.one/snapshots/")
    maven("https://maven.tterrag.com/")
    maven("https://maven.valkyrienskies.org")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
	modImplementation(libs.fabric.loader)
	modImplementation(libs.fabric.api)

    include(modImplementation(libs.portinglib.blocks.get())!!)

    modImplementation(libs.create)

    // VS2 *sigh*
    compileOnly(libs.valkyrienskies.core.api)
    compileOnly(libs.valkyrienskies.core.impl)
    compileOnly(libs.valkyrienskies.core.api.game)
    compileOnly(libs.valkyrienskies.core.util)
    modApi(libs.valkyrienskies.fabric)
}

java {
    val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.processResources {
    val properties = mapOf(
        "version" to version,
        "java_version" to libs.versions.java.get(),
        "fabric_loader_version" to libs.versions.fabric.loader.get(),
        "minecraft_version" to libs.versions.minecraft.get(),
        "fabric_api_version" to libs.versions.fabric.api.get(),
        "create_version" to libs.versions.create.get(),
        "valkyrienskies_version" to libs.versions.valkyrienskies.fabric.get(),
    )

    inputs.properties(properties)
    filesMatching(listOf("fabric.mod.json", "trackwork.mixins.json")) {
        expand(properties)
    }
}
