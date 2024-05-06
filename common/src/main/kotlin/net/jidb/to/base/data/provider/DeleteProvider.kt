package net.jidb.to.base.data.provider

import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteRecursively

class DeleteProvider(val output: PackOutput, val paths: (Path) -> List<Path>) : DataProvider {

    constructor(output: PackOutput, paths: List<Path>) : this(output, { paths })

    @OptIn(ExperimentalPathApi::class)
    override fun run(cached: CachedOutput) = CompletableFuture.runAsync {
        val targets = paths(output.outputFolder)
        for (target in targets) {
            var input: String? = ""
            while (input != null) {
                println("Type 'Y' to delete all contents of '$target', or 'N' to skip.")
                input = readln().uppercase()
                when (input) {
                    "Y" -> {
                        target.deleteRecursively()
                        println("Deleted '$target'.")
                        input = null
                    }
                    "N" -> {
                        input = null
                    }
                }
            }
        }
    }

    override fun getName() = "Remove Data from Folder"
    
    companion object {
        fun factory(paths: (Path) -> List<Path>): (PackOutput) -> DeleteProvider = { DeleteProvider(it, paths) }
        fun factory(paths: List<Path>) = factory { paths }
    }

}