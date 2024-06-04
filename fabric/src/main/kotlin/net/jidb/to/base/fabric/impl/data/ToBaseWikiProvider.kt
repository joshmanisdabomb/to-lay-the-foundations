package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.data.provider.WikiProvider
import net.jidb.to.base.extension.suffix
import net.jidb.to.base.impl.ToBaseBlockLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary
import net.jidb.to.base.wiki.*
import net.jidb.to.base.wiki.builder.WikiArticleBuilder
import net.jidb.to.base.wiki.builder.WikiPageMarkdownBuilder
import net.jidb.to.base.wiki.builder.WikiTranslations
import net.jidb.to.base.wiki.changelog.WikiChangelog
import net.jidb.to.base.wiki.fragment.WikiCompoundFragment
import net.jidb.to.base.wiki.fragment.WikiTextFragment
import net.jidb.to.base.wiki.recipe.WikiRecipes
import net.jidb.to.base.wiki.reference.WikiImage
import net.jidb.to.base.wiki.reference.WikiReference
import net.minecraft.core.registries.BuiltInRegistries
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
                    WikiCompoundFragment(
                        WikiCompoundFragment(
                            WikiTextFragment(Component.literal("This is ")),
                            WikiTextFragment(Component.literal("good"), WikiReference.of(Blocks.DIAMOND_BLOCK)),
                            WikiTextFragment(Component.literal(".")),
                            paragraph = true,
                            heading = EntityType.CREEPER.description
                        )
                    )
                ),
                subjects = listOf(
                    WikiSubject(WikiReference.of(Blocks.DIAMOND_BLOCK), renewable = true, order = 2),
                    WikiSubject(WikiReference.of(Blocks.CHERRY_WOOD), renewable = true, order = 1),
                    WikiSubject(WikiReference.of(ToBaseItemLibrary.test_item), renewable = false, order = 3)
                ),
                header = WikiImage(ResourceLocation(ToBaseMod.MOD_ID, "wasteland_header_image")),
                changelog = WikiChangelog(mapOf()),
                recipes = WikiRecipes(),
                tags = listOf(ResourceLocation(ToBaseMod.MOD_ID, "article"), ResourceLocation("to_dream", "wasteland"))
            )
        ))

        val wt = WikiTranslations()
        val loc2 = loc.suffix("2", "")
        val article2 = WikiArticleBuilder(loc2)
            .addPage(ToBaseMod.MOD_ID, WikiPageMarkdownBuilder()
                .setFile(output.outputFolder.resolve("../assets/${ToBaseMod.MOD_ID}/wiki/test_block.md"))
                .setName(WikiReference.of(ToBaseBlockLibrary.test_block_2))
                .setFlavorText("You won't want to miss this block!")
                .addSubject(WikiSubject(WikiReference.of(ToBaseBlockLibrary.test_block_2), false, 1))
            )
            .build(wt)

        return mapOf(loc to article, loc2 to article2)
    }

}