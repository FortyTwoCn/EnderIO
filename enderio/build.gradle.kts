import com.hypherionmc.modpublisher.properties.ModLoader
import java.net.URI

plugins {
    id("net.neoforged.moddev")
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
}

val minecraftVersion: String by project
val neoForgeVersion: String by project

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

// Mojang ships Java 21 to end users in 1.20.5+, so your mod should target Java 21.
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

println("Building Ender IO version ${project.version}")

configurations {
    create("apiAnnotationProcessor") {
        extendsFrom(annotationProcessor.get())
    }
    create("apiCompileOnly") {
        extendsFrom(compileOnly.get())
    }
    create("apiImplementation") {
        extendsFrom(implementation.get())
    }

    create("datagenAnnotationProcessor") {
        extendsFrom(annotationProcessor.get())
    }
    create("datagenCompileOnly") {
        extendsFrom(compileOnly.get())
    }
    create("datagenImplementation") {
        extendsFrom(implementation.get())
    }
    create("datagenRuntimeOnly") {
        extendsFrom(runtimeOnly.get())
    }
    create("datagenLocalRuntime") {
        extendsFrom(runtimeOnly.get())
    }

    create("gametestAnnotationProcessor") {
        extendsFrom(annotationProcessor.get())
    }
    create("gametestCompileOnly") {
        extendsFrom(compileOnly.get())
    }
    create("gametestImplementation") {
        extendsFrom(implementation.get())
    }
    create("gametestRuntimeOnly") {
        extendsFrom(runtimeOnly.get())
    }
    create("gametestLocalRuntime") {
        extendsFrom(runtimeOnly.get())
    }
}

sourceSets {
    create("api") {
        // No resources.
        resources.setSrcDirs(listOf<String>())
    }

    main {
        resources {
            srcDir("src/generated/resources")
        }

        compileClasspath += sourceSets["api"].output
    }

    create("datagen") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += configurations.getByName("datagenLocalRuntime")

        compileClasspath += sourceSets["api"].output
    }

    create("gametest") {
        compileClasspath += sourceSets.main.get().output
        //runtimeClasspath += configurations.getByName("gametestLocalRuntime")
    }
}

val jeiMinecraftVersion: String by project
val jeiVersion: String by project
val cctMinecraftVersion: String by project
val cctVersion: String by project
val stitchVersion: String by project
val ae2Version: String by project
val refinedstorageVersion: String by project
val jadeFileId: String by project
val mekanismMinecraftVersion: String by project
val mekanismVersion: String by project
val curseforge_laserio_id: String by project
val curseforge_laserio_file: String by project
val almostunifiedVersion: String by project

dependencies {
    // Include modules
//    jarJar(project(":enderio-base"))
//    jarJar(project(":enderio-machines"))
//    jarJar(project(":enderio-conduits"))
//    jarJar(project(":enderio-conduits-modded"))
//    jarJar(project(":enderio-armory"))
//    implementation(project(":enderio-base"))
//    implementation(project(":enderio-machines"))
//    implementation(project(":enderio-conduits"))
//    implementation(project(":enderio-conduits-modded"))
//    implementation(project(":enderio-armory"))

    // JEI
    compileOnly("mezz.jei:jei-$jeiMinecraftVersion-common-api:$jeiVersion")
    compileOnly("mezz.jei:jei-$jeiMinecraftVersion-neoforge-api:$jeiVersion")
    runtimeOnly("mezz.jei:jei-$jeiMinecraftVersion-common:$jeiVersion")
    runtimeOnly("mezz.jei:jei-$jeiMinecraftVersion-neoforge:$jeiVersion")

    // CC: Tweaked
    // TODO: Does not start on latest NeoForge
//    runtimeOnly("cc.tweaked:cc-tweaked-$cctMinecraftVersion-forge:$cctVersion")

    //Athena ctm
    runtimeOnly("maven.modrinth:stitch:${stitchVersion}")

    // AE2
    runtimeOnly("appeng:appliedenergistics2:${ae2Version}")

    // Refined storage
    runtimeOnly("com.refinedmods.refinedstorage:refinedstorage-neoforge:${refinedstorageVersion}")

    // Enchantment descriptions
    //runtimeOnly("net.darkhax.bookshelf:Bookshelf-NeoForge-${minecraft_version}:${bookshelf_version}")
    //runtimeOnly("net.darkhax.enchdesc:EnchantmentDescriptions-NeoForge-${minecraft_version}:${ench_desc_version}")

    // The One Probe https://github.com/McJtyMods/TheOneProbe/issues/548
    //compileOnly("mcjty.theoneprobe:theoneprobe:${top_version}:api") {
    //    transitive = false
    //}
    //runtimeOnly("mcjty.theoneprobe:theoneprobe:${top_version}") {
    //    transitive = false
    //}

    // Jade
    runtimeOnly("curse.maven:jade-324717:${jadeFileId}")

    //fluxnetworks
    ////runtimeOnly("curse.maven:fluxnetworks-248020:4651164")

    // Patchouli
    //runtimeOnly("vazkii.patchouli:Patchouli:${patchouli_version}")

    // Mekanism
    runtimeOnly("mekanism:Mekanism:${mekanismMinecraftVersion}-${mekanismVersion}")

    //Laserio
    runtimeOnly("curse.maven:laserio-${curseforge_laserio_id}:${curseforge_laserio_file}")

    // Almost unified
    compileOnly("com.almostreliable.mods:almostunified-neoforge:1.21.1-${almostunifiedVersion}:api")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

neoForge {
    version = neoForgeVersion

    addModdingDependenciesTo(sourceSets.getByName("api"))
    addModdingDependenciesTo(sourceSets.getByName("datagen"))
    addModdingDependenciesTo(sourceSets.getByName("gametest"))

    accessTransformers {
        publish(project.file("src/main/resources/META-INF/accesstransformer.cfg"))
    }

    mods {
        // define mod <-> source bindings
        // these are used to tell the game which sources are for which mod
        // multi mod projects should define one per mod
        create("enderio") {
            sourceSet(sourceSets["api"])
            sourceSet(sourceSets.main.get())
        }

        create("enderioData") {
            modSourceSets = mods["enderio"].modSourceSets
            sourceSet(sourceSets["datagen"])
        }
    }

    runs {
        configureEach {
            logLevel = org.slf4j.event.Level.INFO
        }

        create("client") {
            client()
        }

        create("server") {
            server()
            gameDirectory = project.file("run/server")
        }

        create("data") {
            data()

            sourceSet = sourceSets["datagen"]
            loadedMods.add(mods["enderioData"])

            programArguments.addAll(
                    "--mod", "enderio",
                    // TODO: Fix missing models...
                    //"--all",
                    "--server", "--client",
                    "--output", file("src/generated/resources").absolutePath,
                    "--existing", file("src/main/resources").absolutePath,
            )
        }
    }
}

// Collect all API packages from all modules.
tasks.register<Jar>("apiJar") {
    archiveClassifier.set("api")

    include("com/enderio/api/**")
    include("com/enderio/*/api/**")
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
}

tasks.build {
    dependsOn(tasks["apiJar"])
    dependsOn(tasks["sourcesJar"])
}

val curseforge_projectId: String by project
val modrinth_projectId: String by project

if (getReleaseType() != null) {
    if (System.getenv("CHANGELOG") != null) {
        publisher {

            apiKeys {
                curseforge(System.getenv("CURSEFORGE_TOKEN"))
                modrinth(System.getenv("MODRINTH_TOKEN"))
            }

            debug.set(System.getenv("PUBLISH") != "true")

            curseID.set(curseforge_projectId)
            modrinthID.set(modrinth_projectId)

            versionType.set(getReleaseType())
            projectVersion.set("${project.version}")

            displayName.set("Ender IO - ${project.version}")
            changelog.set(System.getenv("CHANGELOG"))

            setGameVersions("1.21.1")
            setLoaders(ModLoader.NEOFORGE)

            curseEnvironment.set("both")
            artifact.set(tasks.jar)

            setJavaVersions(JavaVersion.VERSION_21)

            curseDepends {
                optional("jei", /*"patchouli",*/ "stitch", "applied-energistics-2", "mekanism", "cc-tweaked")
            }

            modrinthDepends {
                optional("jei", "stitch", "ae2", "mekanism", "cc-tweaked")
            }
        }
    } else {
        println("Release disabled, no changelog found in environment");
    }
}

fun getReleaseType(): String? {
    // If we"re doing a proper build
    if (System.getenv("BUILD_VERSION") != null) {
        val version_string = System.getenv("BUILD_VERSION")

        if (version_string.lowercase().contains("alpha")) {
            return "alpha"
        } else if (version_string.lowercase().contains("beta")) {
            return "beta"
        }

        return "release"
    }

    return "dev"
}
