loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
}

dependencies {
    common(project(":common", "namedElements")) {
        isTransitive = false
    }
    common(project(":fabric-like", "namedElements")){
        isTransitive = false
    }
    common(project(":forge", "namedElements")){
        isTransitive = false
    }
    common(project(":fabric", "namedElements")){
        isTransitive = false
    }
    common(project(":quilt", "namedElements")){
        isTransitive = false
    }
}

tasks.sourcesJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    val commonSources = project(":common").tasks.getByName<Jar>("sourcesJar")
    dependsOn(commonSources)
    from(commonSources.archiveFile.map { zipTree(it) })

    val fabriclikeSources = project(":fabric-like").tasks.getByName<Jar>("sourcesJar")
    dependsOn(fabriclikeSources)
    from(fabriclikeSources.archiveFile.map { zipTree(it) })

    val forgeSources = project(":forge").tasks.getByName<Jar>("sourcesJar")
    dependsOn(forgeSources)
    from(forgeSources.archiveFile.map { zipTree(it) })

    val fabricSources = project(":fabric").tasks.getByName<Jar>("sourcesJar")
    dependsOn(fabricSources)
    from(fabricSources.archiveFile.map { zipTree(it) })

    val quiltSources = project(":quilt").tasks.getByName<Jar>("sourcesJar")
    dependsOn(quiltSources)
    from(quiltSources.archiveFile.map { zipTree(it) })
}