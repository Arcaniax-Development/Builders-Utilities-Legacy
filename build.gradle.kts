import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("java-library")
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = sourceCompatibility
}

repositories {
    mavenCentral()
    maven {
        name = "Spigot"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "EngineHub"
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        name = "PlotSquared Legacy"
        url = uri("https://plotsquared.com/mvn/")
    }
    maven {
        name = "IntellectualSites 3rd Party"
        url = uri("https://mvn.intellectualsites.com/content/repositories/thirdparty")
    }
    maven {
        name = "OSS Sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
    maven {
        name = "FAWE"
        url = uri("https://ci.athion.net/job/FastAsyncWorldEdit/ws/mvn")
    }
    maven {
        name = "Mojang"
        url = uri("https://libraries.minecraft.net/")
    }
}

dependencies {
    compileOnlyApi("org.spigotmc:spigot-api:1.12-R0.1-SNAPSHOT")
    compileOnly("com.plotsquared:plotsquared-api:3.1")
    compileOnly("com.boydti:fawe-api:latest")
    compileOnlyApi("com.mojang:authlib:1.5.25")
    implementation("de.notmyfault:serverlib:1.0.1")
    implementation("org.bstats:bstats-bukkit:2.2.1")
    implementation("org.bstats:bstats-base:2.2.1")
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set(null as String?)
    dependencies {
        relocate("de.notmyfault", "com.buildersrefuge") {
            include(dependency("de.notmyfault:serverlib:1.0.1"))
        }
        relocate("org.bstats", "com.buildersrefuge.metrics") {
            include(dependency("org.bstats:bstats-base"))
            include(dependency("org.bstats:bstats-bukkit"))
        }
    }
}


group = "com.buildersrefuge"
version = "1.2.4"

tasks.named<Copy>("processResources") {
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }
}

tasks.named("build").configure {
    dependsOn("shadowJar")
}
