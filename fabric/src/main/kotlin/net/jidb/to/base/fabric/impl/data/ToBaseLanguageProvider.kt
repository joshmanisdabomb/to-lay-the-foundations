package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.jidb.to.base.impl.ToBaseCreativeTabLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary

class ToBaseLanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {

    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(ToBaseCreativeTabLibrary.entries["tab"]!!.translationKey, "To Lay the Foundations")

        builder.add(ToBaseItemLibrary.test_item, "Test Item")
    }

}