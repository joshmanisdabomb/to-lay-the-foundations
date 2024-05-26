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
import net.minecraft.data.DataProvider
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

        val post = System.getenv("NET_JIDB_TO_BASE_DATA_API")
        if (post != null) {
            pack.addProvider(DataProvider.Factory {
                HttpPostProvider(it)
                    .setURL(URL(post))
                    .addJsonContent("body", JsonObject().also {
                        it.addProperty("key", System.getenv("NET_JIDB_TO_BASE_DATA_TOKEN"))
                        it.addStringObject("mod", mapOf("id" to ToBaseMod.MOD_ID, "version" to Platform.getMod(ToBaseMod.MOD_ID).version))
                        it.addStringObject("mc", mapOf("id" to "minecraft", "version" to Platform.getMinecraftVersion()))
                    })
                    .addJsonContent("meta", ToBaseWikiMeta.toJson())
                    .addZipContent("content", "content.zip") {
                        val resources = Path.of(it.toString().replace("fabric", "common").replace("generated", "resources"))
                        listOf(resources.resolve("assets/${ToBaseMod.MOD_ID}/wiki") to ToBaseMod.MOD_ID)
                    }
            })
        } else {
            System.err.println("Environment variable NET_JIDB_TO_BASE_DATA_API missing, no POST request will be sent to API.")
        }
    }
}