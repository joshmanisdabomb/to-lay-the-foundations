package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.jidb.to.base.impl.ToBaseBlockLibrary
import net.jidb.to.base.impl.ToBaseCreativeTabLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary

class ToBaseLanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {

    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(ToBaseCreativeTabLibrary.entries["tab"]!!.translationKey, "To Lay the Foundations")

        builder.add(ToBaseItemLibrary.test_item, "Test Item")
        builder.add(ToBaseBlockLibrary.test_block, "Test Block")
        builder.add(ToBaseBlockLibrary.test_block_2, "Test Block 2")
        builder.add(ToBaseBlockLibrary.test_block_3, "Test Block 3")
        builder.add(ToBaseBlockLibrary.test_block_4, "Test Block 4")
    }

}