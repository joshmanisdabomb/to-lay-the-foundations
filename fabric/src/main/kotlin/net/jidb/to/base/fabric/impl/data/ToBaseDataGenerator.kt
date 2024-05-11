package net.jidb.to.base.fabric.impl.data

import com.google.gson.JsonObject
import dev.architectury.platform.Platform
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.data.provider.CopyProvider
import net.jidb.to.base.data.provider.DeleteProvider
import net.jidb.to.base.data.provider.HttpPostProvider
import net.jidb.to.base.extension.addStringObject
import net.minecraft.data.PackOutput
import java.net.URL
import java.nio.file.Path

object ToBaseDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::ToBaseLanguageProvider)
        pack.addProvider(::ToBaseModelProvider)
        pack.addProvider(::ToBaseWikiProvider)
        pack.addProvider(DeleteProvider.factory {
            val resources = Path.of(it.toString().replace("fabric", "common").replace("generated", "resources"))
            val assets = resources.resolve("assets/${ToBaseMod.MOD_ID}")
            listOf("blockstates", "models", "lang").map(assets::resolve)
        })
        pack.addProvider(CopyProvider.factory { Path.of(it.toString().replace("fabric", "common").replace("generated", "resources")) })
        pack.addProvider { output: PackOutput ->
            HttpPostProvider(output)
                .setURL(URL("http://localhost:8000/api/content/update"))
                .addJsonContent("body", JsonObject().also {
                    it.addProperty("key", System.getProperty("net.jidb.to.base.data.token"))
                    it.addStringObject("mod", mapOf("id" to ToBaseMod.MOD_ID, "version" to Platform.getMod(ToBaseMod.MOD_ID).version))
                })
                .addZipContent("content", "content.zip") {
                    val resources = Path.of(it.toString().replace("fabric", "common").replace("generated", "resources"))
                    listOf(resources.resolve("assets/${ToBaseMod.MOD_ID}/wiki") to null)
                }
        }
    }
}