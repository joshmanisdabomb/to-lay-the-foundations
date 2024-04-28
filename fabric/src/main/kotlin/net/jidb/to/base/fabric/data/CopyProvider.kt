package net.jidb.to.base.fabric.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import kotlin.io.path.CopyActionResult
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.copyToRecursively
import kotlin.io.path.deleteRecursively

class CopyProvider(val output: FabricDataOutput, val path: (Path) -> Path) : DataProvider {

    constructor(output: FabricDataOutput, path: Path) : this(output, { path })

    @OptIn(ExperimentalPathApi::class)
    override fun run(cached: CachedOutput) = CompletableFuture.runAsync {
        val target = path(output.outputFolder)
        output.outputFolder.copyToRecursively(target, followLinks = false, overwrite = true)
        target.resolve(".cache").deleteRecursively()
    }

    override fun getName() = "Copy Data to Folder"
    
    companion object {
        fun factory(path: (Path) -> Path): (FabricDataOutput) -> CopyProvider = { CopyProvider(it, path) }
        fun factory(path: Path) = factory { path }
    }

}