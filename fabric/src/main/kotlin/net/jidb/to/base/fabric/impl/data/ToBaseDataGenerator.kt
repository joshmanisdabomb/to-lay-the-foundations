package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.jidb.to.base.fabric.data.provider.CopyProvider
import java.nio.file.Path

object ToBaseDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::ToBaseLanguageProvider)
        pack.addProvider(::ToBaseModelProvider)
        pack.addProvider(CopyProvider.factory { Path.of(it.toString().replace("fabric", "common").replace("generated", "resources")) })
    }
}