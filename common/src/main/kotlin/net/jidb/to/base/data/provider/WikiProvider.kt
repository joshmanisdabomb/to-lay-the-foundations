package net.jidb.to.base.data.provider

import net.jidb.to.base.wiki.WikiArticle
import net.jidb.to.base.wiki.WikiConstantLibrary
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import java.util.concurrent.CompletableFuture

abstract class WikiProvider(val output: PackOutput) : DataProvider {

    abstract fun getArticles(): Map<ResourceLocation, WikiArticle>

    open fun getPathProvider() = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "wiki")

    override fun run(writer: CachedOutput): CompletableFuture<*> {
        WikiConstantLibrary.buildOnce()

        val path = getPathProvider()
        return CompletableFuture.allOf(*getArticles().map { (k, v) ->
            DataProvider.saveStable(writer, v.toJson(), path.json(k))
        }.toTypedArray())
    }

    override fun getName() = "To Wiki Article Definitions"

}