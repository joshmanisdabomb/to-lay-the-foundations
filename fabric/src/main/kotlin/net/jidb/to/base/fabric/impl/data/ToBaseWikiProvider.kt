package net.jidb.to.base.fabric.impl.data

import com.google.gson.Gson
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.data.models.ExtendedBlockModelGenerator
import net.jidb.to.base.data.provider.WikiProvider
import net.jidb.to.base.impl.ToBaseBlockLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary
import net.jidb.to.base.wiki.*
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.Blocks

class ToBaseWikiProvider(output: FabricDataOutput) : WikiProvider(output) {

    override fun getArticles(): Map<ResourceLocation, WikiArticle> {
        val loc = ResourceLocation(ToBaseMod.MOD_ID, "test")
        val article = WikiArticle(loc, mapOf(
            ToBaseMod.MOD_ID to WikiPage(
                name = Component.literal("Test Page").append("abc"),
                flavor = Component.literal("The first of many!"),
                content = WikiContent(
                    WikiCompoundFragnent(
                        WikiCompoundFragnent(
                            WikiTextFragnent(Component.literal("This is ")),
                            WikiTextFragnent(Component.literal("good"), WikiDestination(BuiltInRegistries.BLOCK, Blocks.DIAMOND_BLOCK)),
                            WikiTextFragnent(Component.literal(".")),
                            paragraph = true,
                            heading = EntityType.CREEPER.description
                        )
                    )
                ),
                subjects = listOf(
                    WikiSubject(BuiltInRegistries.BLOCK, Blocks.DIAMOND_BLOCK, renewable = true, order = 2),
                    WikiSubject(BuiltInRegistries.BLOCK, Blocks.CHERRY_WOOD, renewable = true, order = 1),
                    WikiSubject(BuiltInRegistries.ITEM, ToBaseItemLibrary.test_item, renewable = false, order = 3)
                ),
                header = WikiImage(ResourceLocation(ToBaseMod.MOD_ID, "wasteland_header_image")),
                changelog = WikiChangelog(mapOf()),
                recipes = WikiRecipes(),
                tags = listOf(ResourceLocation(ToBaseMod.MOD_ID, "article"), ResourceLocation("to_dream", "wasteland"))
            )
        ))
        return mapOf(loc to article)
    }

}