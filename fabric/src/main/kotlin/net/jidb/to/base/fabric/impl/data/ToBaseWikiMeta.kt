package net.jidb.to.base.fabric.impl.data

import dev.architectury.platform.Platform
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.wiki.meta.ToWikiMeta
import net.jidb.to.base.wiki.meta.ToWikiMod
import net.jidb.to.base.wiki.meta.ToWikiModVersion
import java.time.ZoneId
import java.time.ZonedDateTime

object ToBaseWikiMeta : ToWikiMeta {

    val toBase = ToWikiMod(
        Platform.getMod(ToBaseMod.MOD_ID),
        short = "ToLTF",
        repository = "joshmanisdabomb/to-lay-the-foundations",
        versions = listOf(
            ToWikiModVersion(
                code = "0.1.0",
                mcVersion = "1.20.4",
                commit = "d3c485f0391802f8dda9f806c3c0f98dfad34e8c",
                released = ZonedDateTime.of(2024, 4, 28, 21, 55, 7, 0, ZoneId.of("UTC")),
                changelog = """Initial commit from Kotlin architectury template.
Renamed files to use to_base mod ID and added my own values into code and cleaned it up a bit also.
Created hybrid Gradle subproject to combine all sources in one jar. Added fabric-like sources to fabric and quilt jars.
Exporting mod builds to wiki on GitHub build.
Added libraries (previously directories from LCC) to keep deferred registries of blocks, items, creative tabs, etc."""
            ),
            ToWikiModVersion(
                code = "0.2.0",
                mcVersion = "1.20.4",
                commit = "dd3df54c9b9afdb7d529964dae3d4a055f45b7fd",
                released = ZonedDateTime.of(2024, 5, 2, 23, 48, 22, 0, ZoneId.of("UTC")),
                changelog = """Added Fabric data generator for language and model files.
Upgraded Library system to have simple and advanced libraries. Simple libraries passthru the initial type to the final symbol type.
Reimplemented some extension Kotlin files from LCC infra.
Reimplemented test blocks 1-4 with data generation.
Added item block library that can automatically generate and manually replace and remove block items for blocks from a library.
Added delete file data provider."""
            ),
            ToWikiModVersion(
                code = "0.3.0",
                mcVersion = "1.20.4",
                commit = "54cefdc269787edec41b894423a265c2b1ff6a68",
                released = ZonedDateTime.of(2024, 5, 11, 21, 22, 18, 0, ZoneId.of("UTC")),
                changelog = """Moved data generation stuff that could theoretically be used outside of Fabric to the common source set.
Reimplemented wiki system with final classes, with a test JSON page created in data generation. Added JSON helper class and extension functions.
Added article builders to read from a markdown page with inline JSON fragment templates.
Added HTTP post provider that can send multipart form requests to a server."""
            ),
            ToWikiModVersion(
                code = "0.4.0",
                mcVersion = "1.20.4",
                commit = null,
                released = null,
                changelog = """"""
            )
        )
    )

    val lcc = ToWikiMod(
        identifier = "lcc",
        name = "Loosely Connected Concepts (Fabric)",
        short = "LooselyConnectedConcepts",
        repository = "joshmanisdabomb/loosely-connected-concepts",
        repositoryBranch = "fabric",
        legacy = true,
        tags = true,
        sources = true,
        modrinth = false,
        curseforge = false,
        versions = listOf(
            ToWikiModVersion(
                code = "0.1.0",
                mcVersion = "20w51a",
                commit = "96d06e47820429b3c3fa54b1b333266e1dd7be28",
                released = ZonedDateTime.of(2020, 12, 21, 1, 20, 11, 0, ZoneId.of("Europe/London")),
                changelog = """Added iron, crystal and temporary hearts.
Starting wasteland biome.
Added test blocks and cracked mud.
Test block 5 connected texture.
Added tools and armour real quick like.
Implementing roads made from cooled asphalt.
Soaking soul sand with fizzy bubbles.
Added arcane table block, block entity, container and screen.
Added bounce pads.
Gauntlet work.
To allow running in real environments: No longer use reflection for thing directories.
Time rift functionality and ruby recipes."""
            ),
            ToWikiModVersion(
                code = "0.1.1",
                mcVersion = "20w51a",
                commit = "88874f3bf4e67d0fdfd4a91741d5ba519cd2fe16",
                released = ZonedDateTime.of(2020, 12, 22, 20, 9, 35, 0, ZoneId.of("Europe/London")),
                changelog = """Infrastructure for item rendering. Time rift rendering.
Added topaz geodes naturally spawning in some lava lakes.
Reworked gem system, rubies and diamonds tier 3, emerald and sapphire tier 2, amethyst and topaz tier 1.
Retextured amethyst equipment and topaz shards.
Time rift model.
Added topaz and reworked gems into tiers."""
            ),
            ToWikiModVersion(
                code = "0.1.2",
                mcVersion = "20w51a",
                commit = "c6b21a3ec702f0a24181c2dbfc92eef98d7fa2ec",
                released = ZonedDateTime.of(2020, 12, 24, 15, 20, 33, 0, ZoneId.of("Europe/London")),
                changelog = """Fixed topaz clusters not dropping crystals.
Added pumice and rhyolite to topaz geode casing.
Added textures and functionality to many classic blocks previously added."""
            ),
            ToWikiModVersion(
                code = "0.2.0",
                mcVersion = "20w51a",
                commit = "6b3015156f48436ebe651985004f28e69508d523",
                released = ZonedDateTime.of(2021, 1, 2, 4, 21, 19, 0, ZoneId.of("Europe/London")),
                changelog = """Internally modularised the modification using the MD5 protocol.
Added classic chest, nether reactor, crying obsidian.
Fixed topaz geode casing not generating enough topaz."""
            ),
            ToWikiModVersion(
                code = "0.2.1",
                mcVersion = "20w51a",
                commit = "c6f81c551ccbb77f990d4c9619196891ced67c2f",
                released = ZonedDateTime.of(2021, 1, 3, 14, 35, 23, 0, ZoneId.of("Europe/London")),
                changelog = """Added classic foods and the quiver.
Fixed bug with spawning mixin for classic crying obsidian.
Time rift no longer instantly breaks.
Fixed language file and data generation issues.
Changed texture for rhyolite."""
            ),
            ToWikiModVersion(
                code = "0.2.2",
                mcVersion = "20w51a",
                commit = "f9b2c8e6edfffa692e9aa2cae639a3b7306fadc9",
                released = ZonedDateTime.of(2021, 1, 8, 13, 18, 29, 0, ZoneId.of("Europe/London")),
                changelog = """Added cogs, classic leather armors and classic fish raw and cooked.
Fixed server crash with client packets such as bounce pad extensions.
Fixed furnace recipes for smelting items."""
            ),
            ToWikiModVersion(
                code = "0.3.0",
                mcVersion = "20w51a",
                commit = "f2feabf9accd6d6b8e3c4cb4117f3a5c0e13260a",
                released = ZonedDateTime.of(2021, 1, 13, 20, 30, 36, 0, ZoneId.of("Europe/London")),
                changelog = """Added refiner, power cables and solar panels.
Added heavy uranium."""
            ),
            ToWikiModVersion(
                code = "0.3.1",
                mcVersion = "20w51a",
                commit = "9bd1a12fbe128ae2d6dc8e9c27b80ad1492b3f88",
                released = ZonedDateTime.of(2021, 1, 15, 0, 2, 42, 0, ZoneId.of("Europe/London")),
                changelog = """Fixed refiner numbers displaying incorrectly on multiplayer servers.
Quiver now stores picked up arrow items and projectiles.
Fixed space of bundle required by quiver.
Cogs now check if location is valid when surroundings change.
Cogs now drop correct amount of items when broken by the game.
Update to latest Fabric."""
            ),
            ToWikiModVersion(
                code = "0.3.2",
                mcVersion = "21w03a",
                commit = "4da91a3ad46da6d94e19a126d31d085ac46eda9f",
                released = ZonedDateTime.of(2021, 1, 25, 18, 8, 51, 0, ZoneId.of("Europe/London")),
                changelog = """Added furnace and combustion generators.
Added silicon for solar panel recipe and geothermal power.
Added energy bank and batteries for storing power."""
            ),
            ToWikiModVersion(
                code = "0.4.0",
                mcVersion = "21w06a",
                commit = "5740b71206a282c60f661c30ee1120a158e9dcc0",
                released = ZonedDateTime.of(2021, 2, 15, 13, 29, 2, 0, ZoneId.of("Europe/London")),
                changelog = """Added atomic bomb and radiation sickness.
Using nightly Cardinal Components."""
            ),
            ToWikiModVersion(
                code = "0.4.1",
                mcVersion = "21w06a",
                commit = "d6e74fb449804b7ca8764904be23deada208c312",
                released = ZonedDateTime.of(2021, 2, 16, 21, 25, 52, 0, ZoneId.of("Europe/London")),
                changelog = """Atomic bomb particles.
Achievement for atomic bomb.
Radiation increase when within epicenter of atomic bomb.
Atomic bombs and nuclear explosions now load chunks.
Fix for server crash on radiation increase."""
            ),
            ToWikiModVersion(
                code = "0.4.2",
                mcVersion = "21w08b",
                commit = "3dadf281846b069337fb43911667eaaad5508270",
                released = ZonedDateTime.of(2021, 3, 5, 17, 16, 53, 0, ZoneId.of("Europe/London")),
                changelog = """Hazmat suit now blocks new status effects when oxygenated full set.
Hazmat helmet blocks eating food. Update to 21w08b and latest Kotlin.
Added oxygen tank and oxygen extractor. Hazmat suit recipes bar chestplate. LCC Base can display multiple bars for items.
Adding rubber wood, rubber tree blocks and treetap and latex gathering.
Migrated heart trackers to components. Ready to migrate from gauntlet trackers to components.
Rewriting gauntlet functionality for components, uppercut done - punch next.
Blocks, items and nullable blockitems now working with new directory system.
Race advancement for nuclear detonation. Fixed bug with nuclear explosion log not displaying name of player."""
            ),
            ToWikiModVersion(
                code = "0.4.3",
                mcVersion = "21w11a",
                commit = "5bf08e56d471f92f0d2b40e6faec459502071196",
                released = ZonedDateTime.of(2021, 3, 29, 1, 30, 5, 0, ZoneId.of("Europe/London")),
                changelog = """Added nuclear power generation and heavy uranium shielding to protect against nuclear explosions.
Added radiation and contained armour HUD elements.
Added graphics and tooltip to oxygen extractor.
Hazmat suits expend more oxygen when player is active and protects against more hazards.
Added kiln block for quicker smelting of non-blastables and non-smokables.
Added slabs and stairs for rhyolite and classic blocks.
Nerfed latex and solar power production.
Added custom heart type damage sounds and fixed custom heart type damage overlay.
Updated to 21w11a."""
            ),
            ToWikiModVersion(
                code = "0.4.4",
                mcVersion = "21w14a",
                commit = "9bab2b1a0257bc73292ef693771e2a664d60a80a",
                released = ZonedDateTime.of(2021, 4, 29, 15, 37, 7, 0, ZoneId.of("Europe/London")),
                changelog = """Added salt to gauge block temperatures and to throw at mobs and players.
Added tungsten ore in wasteland, raw tungsten item and block, tungsten ingot and block, cut tungsten, stairs and slabs.
Added radar and alarm.
Added radiation detector to detect nuclear winter level, nearby uranium and other radioactive sources.
Added rubber sign and rubber boat.
Added rubber block.
Nuclear generators now spawn nuclear waste below themselves that must be handled by the player.
Nuclear waste now piles up by horizontally sliding when landing.
Uranium ore now almost exclusively spawns exposed to air.
Added radiation and nuclear winter command.
Nuclear winters now spawn more mobs with effects per level, and causes weather issues.
Added advancements.
Fixed bug with radiation not healing and large amounts not carrying over to the next life.
Update to 21w14a."""
            ),
            ToWikiModVersion(
                code = "0.5.0",
                mcVersion = "21w14a",
                commit = "36b860424b6eb4c636866382f341b3794f99d76b",
                released = ZonedDateTime.of(2021, 8, 19, 13, 52, 40, 0, ZoneId.of("Europe/London")),
                changelog = """Added sapphire altar block, structure, brick blocks with stair, slab and wall variants.
Added iron oxide nuggets used for making challenge keys.
Added dull sapphires exclusively for charging into regular sapphires.
Added structure rotation and mirror support for directional blocks.
Added altar challenge registry for loading challenges. Added minesweeper altar challenge. Added bomb board blocks.
Added crowbar that can stun on critical hits.
Deadwood logs now spawn in the wasteland.
Fixed oil spawning in exposed locations, now grouped in clusters scattered around the wasteland.
Added tent structures to wasteland with random loot.
Added shattered glass which crumbles under impact.
Spike traps now spawn in the wasteland barrens.
Landmines now spawn in the wasteland.
Added consumers that fire their tongues at entities.
Oil refining now provides tar for asphalt, refined oil buckets for plastic, and fuel for explosive paste (currently).
Products from distillation can also be stepped up with cracking recipes.
Plastic which gets vibrant colour from dyes in refining recipe, doesn't despawn.
Added plastic bag to store 128 items without despawning.
Can now define complex refining recipes.
Added rubber piston block and textures.
Added explosive paste which allows chain reaction explosions.
Fixed bug with custom recipes only giving one output for all recipes.
Added rusted iron blocks to wasteland. Added rusted iron bars.
Added polished fortstone with connected texture.
Added fortstone, cobbled fortstone and generation.
Separated wasteland spikes and barrens biomes.
Added baby skeleton entity.
Added wasp entity, renderer and model.
Added deposit blocks for wasteland.
Added wasteland spawn eggs for baby skeleton, wasp and consumer.
Split lcc-hooks into separate module.
Content datagen now launching and matches 0.4.4 datagen."""
            ),
            ToWikiModVersion(
                code = "0.5.1",
                mcVersion = "1.19.2",
                commit = "2c3af4ae844662a69dbcd78de397af83ba3f8e5a",
                released = ZonedDateTime.of(2022, 10, 9, 12, 50, 40, 0, ZoneId.of("Europe/London")),
                changelog = """Updated to 1.19.2
Added a wiki with article writing system.
Added Cracked Mud recipes and Cracked Mud turning into Mud when near water.
Fixed bug with bottom slab arcane table being placeable on top of itself.
Adding enhancing pyre.
Added heart container recipes with beta enhancing dust.
Added Disciple to heal other mobs with 1000 IQ brain.
Fixed Consumer tongue crashing the client.
Added Psycho pig entity.
Added Knife item when used by player and Psycho Pig now inflicts bleeding and deals no knockback to entities.
Added clover flower block that gives luck and unluck to nearby players.
Rubber pistons now launch entities and stationary falling blocks.
Added forget me not decorative flower.
Changed bounce pad to have rubber face and use rubber piston in recipe.
Added calendar item.
Added rotwitch entity.
Added flies which fight for their owner.
Added infested treasure enchantment.
Added enhancing chamber block and entity.
Added overleveled enchantments special recipe using omega pyre.
Adding imbuing press block and imbuing recipes to imbue weapons.
Stinger can now imbue weapons with Poison II for 5 seconds.
Stinger now has 3 durability.
Added magnetic iron and attract and repulse magnet blocks.
Added scroll of reconditioning made with forget me not to make mobs forget hostilities and villagers forget their professions.
Added woodlouse entity as a wasteland passive mob.
Converted wasteland damage and protection into entity attributes, which wasteland equipment provides.
Minesweeper altar challenges now make sure they're solvable before generating.
Adding arena altar challenges to face wasteland mobs in a dungeon.
Added villager traveller who can find the wasteland.
Bundles now craftable.
Added fly egg item as Rotwitch drop
Added Spawning Pit block"""
            ),
            ToWikiModVersion(
                code = "0.5.2",
                mcVersion = "1.19.2",
                commit = null,
                released = null,
                changelog = """Added item magnet to attract items towards the player.
Added Rotwitch, Fly, Fly Eggs, Item Magnet and LCC 0.5.1 knowledge article.
Added legacy information to Psycho Pig knowledge article.
Reinstated knowledge article for LCC 0.5.0
Added polished sapphire altar brick and heart condenser.
Added sapphire altar brick and polished sapphire altar brick knowledge articles.
Heart Condenser knowledge article and new method for getting all recipes of a type.
Added the hunter entity, the boring mob that the wasteland needs.
WIP Consumer Maw item."""
            ),
        ))

    val lccForge = ToWikiMod(
        identifier = "lcc_forge",
        name = "Loosely Connected Concepts (Forge)",
        short = "LooselyConnectedConcepts",
        repository = "joshmanisdabomb/loosely-connected-concepts",
        repositoryBranch = "lcc1.15.2forge",
        legacy = true,
        tags = false,
        sources = false,
        modrinth = false,
        curseforge = false,
        versions = listOf(
            ToWikiModVersion(
                code = "prealpha",
                name = "Pre-Alpha",
                mcVersion = "1.13.2",
                commit = "f0f36ef34a0112b85fe2d06dfc5256f9a6679767",
                released = ZonedDateTime.of(2019, 2, 17, 20, 28, 13, 0, ZoneId.of("Europe/London")),
                changelog = """Pretty empty with nothing added."""
            ),
            ToWikiModVersion(
                code = "a0.1",
                name = "Alpha 0.1",
                mcVersion = "1.13.2",
                commit = "29eead077eaf7d7dcaf8354afdf588ccf001a8d2",
                released = ZonedDateTime.of(2019, 2, 18, 19, 4, 1, 0, ZoneId.of("Europe/London")),
                changelog = """Registers blocks, itemblocks and items.
Language file.
Textures, models and blockstates working!
Implemented all test blocks apart from connected."""
            ),
            ToWikiModVersion(
                code = "a0.2",
                name = "Alpha 0.2",
                mcVersion = "1.13.2",
                commit = "098ad4a1a64f54f0f1c7f3f209069b14853ed40f",
                released = ZonedDateTime.of(2019, 2, 19, 21, 20, 23, 0, ZoneId.of("Europe/London")),
                changelog = """Implemented creative tab.
Resources textured and modelled.
Added entity loading and a nuclear explosion entity.
Updated all textures to fit 1.14 style. Added enriched uranium resource."""
            ),
            ToWikiModVersion(
                code = "a0.3",
                name = "Alpha 0.3",
                mcVersion = "1.13.2",
                commit = "de1a61290cf5e09dac4f90c3d983231ebe71c4b5",
                released = ZonedDateTime.of(2019, 2, 20, 21, 34, 13, 0, ZoneId.of("Europe/London")),
                changelog = """Implemented nuclear explosion semi-functionality and optimisation.
Added nuclear debris and fire to nuclear main explosion.
Generating ores in the world."""
            ),
            ToWikiModVersion(
                code = "a0.4",
                name = "Alpha 0.4",
                mcVersion = "1.14.3",
                commit = "247076b394b96078a282ab1b56782228e0742571",
                released = ZonedDateTime.of(2019, 7, 20, 0, 12, 9, 0, ZoneId.of("Europe/London")),
                changelog = """Initial refactoring for 1.14.3.
Added the gauntlet with arm rendering technology.
Added capability structure, added gauntlet functionality.
Added gauntlet UI overlay to show cooldowns and actions.
Added stun potion effect, not fully working yet.
Added gem modifiers to the gauntlet and implemented temporary hearts and structure for other hearts.
Added millions of item models for gem tools and armour.
Better way to sort and add empty space to item group.
Implemented heart items and containers. Sync heart data using packets and fixed heart using desync."""
            ),
            ToWikiModVersion(
                code = "a0.5",
                name = "Alpha 0.5",
                mcVersion = "1.14.4",
                commit = "6cc072427f5c8f2f5d051c4dfbd538d65a123ad7",
                released = ZonedDateTime.of(2019, 8, 17, 1, 29, 29, 0, ZoneId.of("Europe/London")),
                changelog = """Updated to 1.14.4 and a ton of refactoring!
Added survival crafting recipes and loot tables.
Complete renaming files to 1.14 naming conventions.
Adding survival-based block properties.
Small changes to nuclear explosion and fire.
Gauntlet functionality now plays nice on multiplayer.
Added nuclear explosion model (needs work)
Added particle effect packet handler ready for gauntlet client effects.
Added particle effects and sounds to gauntlet.
Finished connected texture blocks.
Welcome spreader blocks and spreader interface to the mod. Spreader interface now change the world's global capability."""
            ),
            ToWikiModVersion(
                code = "a1.0",
                name = "Alpha 1.0",
                mcVersion = "1.14.4",
                commit = "f8af04780f006448e9d2cbacd2f392e74ef7c7e3",
                released = ZonedDateTime.of(2019, 10, 4, 2, 14, 37, 0, ZoneId.of("Europe/London")),
                changelog = """Added basic multipart block interface, added cog dynamic model.
Added block networks for cogs and computers later.
Crying Obsidian test with respawning in different dimension.
Adding nostalgic items to the mod.
The glove finally fits.
Added the time rift, capable of nostalgic crafting.
Added road block and updated connected texture methods to support different states and block heights. Road blocks now speed up entities and boats.
Added bounce pads and updated working for multiplayer. Particles trigger in singleplayer. Code cleanup and spreader interface texture update.
Added hydrated soul sand and rewrote my tab sorting system from 3 years ago!"""
            ),
            ToWikiModVersion(
                code = "b1.0",
                name = "Beta 1.0",
                mcVersion = "1.15.2",
                commit = "46f11344c13473cc15704d3011360fd8038d6943",
                released = ZonedDateTime.of(2020, 5, 8, 12, 48, 31, 0, ZoneId.of("Europe/London")),
                changelog = """Beginning creation of computers. Added digital storage items.
Adding the wasteland biome. Added oil to wasteland biome. Oil interactions with other liquids.
Adding atomic bomb shell.
Added rainbow dimension. Added rainbow dimension biomes, rainbow grasses, sparkling dirt, twilight stone, candy canes. Added small and large candy canes.
Added Rainbow Gate. Added Rainbow Portal block and fixed rendering issues with rainbow gate.
Working on channelite. Star plating block and tag and loot table updates.
Added flammable and vulnerable potion effects.
Stun now locks mouse movement.
Fix to double classic chest and hopper interaction.
Added loot table, lang, block state, item model, tag, advancement data generator."""
            ),
        ))

    val aimagg = ToWikiMod(
        identifier = "aimagg",
        name = "Aimless Agglomeration",
        short = "AimlessAgglomeration",
        repository = "joshmanisdabomb/loosely-connected-concepts",
        repositoryBranch = "aa1.12.2",
        legacy = true,
        tags = false,
        sources = true,
        modrinth = false,
        curseforge = false,
        versions = listOf(
            ToWikiModVersion(
                code = "prealpha",
                name = "Pre-Alpha",
                mcVersion = "1.10.2",
                commit = "7fd7eb5395ea3a9b2ad3e29518ff2facd7cecc26",
                released = ZonedDateTime.of(2016, 10, 26, 22, 16, 51, 0, ZoneId.of("Europe/London")),
                changelog = """Added a creative tab with a custom sort system, and a directional facing test block. Starting work on a spreader constructor, which will allow you to build spreaders that infect the world."""
            ),
            ToWikiModVersion(
                code = "a0.1",
                name = "Alpha 0.1",
                mcVersion = "1.10.2",
                commit = "2f763d885ec444076450853dc79d07212bf1ff64",
                released = ZonedDateTime.of(2016, 11, 9, 14, 44, 42, 0, ZoneId.of("Europe/London")),
                changelog = """Missile movement works, item and entity model for missiles."""
            ),
            ToWikiModVersion(
                code = "a0.1.1",
                name = "Alpha 0.1.1",
                mcVersion = "1.10.2",
                commit = "0cc3938d4a2889e08194769ba91044e28830a0db",
                released = ZonedDateTime.of(2016, 11, 19, 17, 51, 54, 0, ZoneId.of("Europe/London")),
                changelog = """Missile polish, trying to add particle effects. Spreader blocks suspended, work begun on Billie Blocks. Changed upgrade system to card system, launch pad now renders further away, nuclear explosions less laggy, fire missile model and nuclear waste model added."""
            ),
            ToWikiModVersion(
                code = "a0.2",
                name = "Alpha 0.2",
                mcVersion = "1.12",
                commit = "28213f9f0128ba27697d4416606a7b7e445e1daf",
                released = ZonedDateTime.of(2017, 7, 26, 4, 35, 20, 0, ZoneId.of("Europe/London")),
                changelog = """My first successful port, for 1.12. Added pills and making custom models look nice in all situations."""
            ),
            ToWikiModVersion(
                code = "a1.0.0",
                name = "Alpha 1.0.0",
                mcVersion = "1.12",
                commit = "927612e2f4dcbe119488613c147b57cc733c4b50",
                released = ZonedDateTime.of(2017, 7, 27, 3, 22, 2, 0, ZoneId.of("Europe/London")),
                changelog = """Everything renders correctly apart from launch pad."""
            ),
            ToWikiModVersion(
                code = "a1.0.1",
                name = "Alpha 1.0.1",
                mcVersion = "1.12",
                commit = "d78295320d9205216cda859eef7a1fba16be8fc4",
                released = ZonedDateTime.of(2017, 7, 27, 7, 47, 37, 0, ZoneId.of("Europe/London")),
                changelog = """Added particles to modelled blocks and updated rotations in GUI.
No more modelling errors in logs.
Updated unrefined uranium texture."""
            ),
            ToWikiModVersion(
                code = "a1.0.2",
                name = "Alpha 1.0.2",
                mcVersion = "1.12",
                commit = "4d3f782916f2629395a23a355669e20c4d64412f",
                released = ZonedDateTime.of(2017, 7, 27, 10, 35, 33, 0, ZoneId.of("Europe/London")),
                changelog = """Missile launch pad now has shift click support.
Client and server communicate so missile can be seen.
Missile renders on client."""
            ),
            ToWikiModVersion(
                code = "a1.1.0",
                name = "Alpha 1.1.0",
                mcVersion = "1.12",
                commit = "6e4156f8e65f24e4110c28274af39ef748dcd68c",
                released = ZonedDateTime.of(2017, 8, 2, 22, 21, 13, 0, ZoneId.of("Europe/London")),
                changelog = """Added mud, quicksand, 2 types of hearts that render in-game, 3 types of heart consumables, crafting materials.
Added metadata sensitive block hardness, resistance, map colours, block sounds, harvest types and levels.
Added drop types and silk touch support.
Added enum support in favour of just knowing what the numbers mean.
Added shift click support for containers. They still need loads of work."""
            ),
            ToWikiModVersion(
                code = "a1.1.1",
                name = "Alpha 1.1.1",
                mcVersion = "1.12",
                commit = "6dbf5f5bacc5445a7c241fcf6a003b7cf3defa9a",
                released = ZonedDateTime.of(2017, 8, 2, 22, 26, 5, 0, ZoneId.of("Europe/London")),
                changelog = """Quick language file fix."""
            ),
            ToWikiModVersion(
                code = "a1.2",
                name = "Alpha 1.2",
                title = "Connected Textures",
                mcVersion = "1.12",
                commit = "c3d22a4ed52537ac0f39d3fd47151d3f8feab936",
                released = ZonedDateTime.of(2017, 8, 31, 21, 30, 17, 0, ZoneId.of("Europe/London")),
                changelog = """Overhauled sorting system, with categories, upper and lower sorting values.
Added different basic types of blocks, including connected texture blocks. (not finished yet)
Added pill effect GUI overlay and more pill effects work.
Starting work on rainbow dimension."""
            ),
            ToWikiModVersion(
                code = "a1.3",
                name = "Alpha 1.3",
                title = "Touch the Rainbow",
                mcVersion = "1.12",
                commit = "669eab2d8c93fe792a8ebaef91890672686d220d",
                released = ZonedDateTime.of(2017, 9, 11, 21, 49, 56, 0, ZoneId.of("Europe/London")),
                changelog = """Currently in process of adding different rainbow dimension blocks.
Officially added the rainbow dimension and have working world gen.
Added spike blocks.
Changed unlocalized name formats."""
            ),
            ToWikiModVersion(
                code = "a1.3.1",
                name = "Alpha 1.3.1",
                mcVersion = "1.12",
                commit = "e38c17eaf73cf0635cfe1fe81735910586d23e61",
                released = ZonedDateTime.of(2017, 9, 12, 21, 20, 7, 0, ZoneId.of("Europe/London")),
                changelog = """Chocolate blocks given model and localised name.
Rainbow dimension has custom caves."""
            ),
            ToWikiModVersion(
                code = "a1.3.2",
                name = "Alpha 1.3.2",
                mcVersion = "1.12",
                commit = "b99ba5c406ad147373db9464d942ab5292fe5f88",
                released = ZonedDateTime.of(2017, 9, 12, 22, 15, 35, 0, ZoneId.of("Europe/London")),
                changelog = """More reasonable gemstone ore generation."""
            ),
            ToWikiModVersion(
                code = "a1.4",
                name = "Alpha 1.4",
                title = "The Block Update",
                mcVersion = "1.12",
                commit = "34815565e90c12b8d28c8da13e2c1742c9826589",
                released = ZonedDateTime.of(2017, 9, 22, 16, 16, 3, 0, ZoneId.of("Europe/London")),
                changelog = """Added illuminant tiles, scaffolding blocks, fortstone, candy canes, refined candy cane blocks, jelly.
Overhauled blockstates, block models and item models to forge's system, deleting 35+ model files."""
            ),
            ToWikiModVersion(
                code = "a1.4.1",
                name = "Alpha 1.4.1",
                mcVersion = "1.12",
                commit = "0736b1109d2d47e7f66cac44b74f4982523e30af",
                released = ZonedDateTime.of(2017, 9, 23, 2, 33, 31, 0, ZoneId.of("Europe/London")),
                changelog = """Overhauled spreader textures and models. Saved about 3 MB of space!
Starting work on neon ore."""
            ),
            ToWikiModVersion(
                code = "a1.5",
                name = "Alpha 1.5",
                title = "New Meets Old",
                mcVersion = "1.12.2",
                commit = "eb1bfc252a98903c420a5974fc895fe9f960aac3",
                released = ZonedDateTime.of(2017, 10, 10, 14, 58, 8, 0, ZoneId.of("Europe/London")),
                changelog = """Added gemstone and neon tools and armour, with recipes! The mod is starting to become more survival friendly.
Added rainbow core ore and lollipop stick.
Added classic grass, porkchops, and two sets of wool.
Started work on computing."""
            ),
            ToWikiModVersion(
                code = "a1.6",
                name = "Alpha 1.6",
                title = "Survival Spark",
                mcVersion = "1.12.2",
                commit = "cddd74737e03e957aaa83cc3b131492e4af0adec",
                released = ZonedDateTime.of(2017, 10, 23, 23, 1, 7, 0, ZoneId.of("Europe/London")),
                changelog = """Began work on the wasteland biome. Added cracked mud for the biome.
Added bounce pads and spring items.
Added computer cases and computer cables.
Added classic blocks, including classic saplings and leaves.
Multiplayer servers now run and should continue to run as well!
Using predicates instead of comparing one block state to another.
Added loads of crafting recipes!
Changed text colours of item tooltips.
Updated textures of neon equipment."""
            ),
            ToWikiModVersion(
                code = "a1.6.1",
                name = "Alpha 1.6.1",
                mcVersion = "1.12.2",
                commit = "cb090c078421af02e42dd2c67bbc1db30cffe633",
                released = ZonedDateTime.of(2017, 10, 25, 18, 30, 32, 0, ZoneId.of("Europe/London")),
                changelog = """Fixed connected texture blocks, not touching them again for at least seven years.
Updated textures for fortstone and rainbow pads."""
            ),
        ))

    val yam = ToWikiMod(
        identifier = "yam",
        name = "Yet Another Mod",
        short = "YAM",
        repository = "joshmanisdabomb/loosely-connected-concepts",
        repositoryBranch = "yam1.7.2",
        legacy = true,
        tags = false,
        sources = false,
        modrinth = false,
        curseforge = false,
        versions = listOf(
            ToWikiModVersion(
                code = "u1",
                name = "Update 1",
                mcVersion = "1.7.2",
                commit = "5a3214c70c6a0ddfbaf548e5a8b57b9f88430ff1",
                released = ZonedDateTime.of(2015, 2, 15, 19, 9, 2, 0, ZoneId.of("Europe/London")),
                changelog = """First version uploaded, reports itself as Beta 1.3."""
            ),
            ToWikiModVersion(
                code = "u1.1",
                name = "Update 1.1",
                mcVersion = "1.7.2",
                commit = "ff82e62acf6decad6030b8737f990a9db4a758e0",
                released = ZonedDateTime.of(2015, 2, 15, 21, 14, 56, 0, ZoneId.of("Europe/London")),
                changelog = """Removed Mars and Mercury dimensions, replaced with 'Asmia' dimension(?)"""
            ),
            ToWikiModVersion(
                code = "u2",
                name = "Update 2",
                mcVersion = "1.7.2",
                commit = "511bf0f6e433fb93565052f350a07fdfd49e4f4d",
                released = ZonedDateTime.of(2015, 2, 16, 17, 34, 32, 0, ZoneId.of("Europe/London")),
                changelog = """Added the tick mob to the wasteland, changed sounds of Amplislimes, spawn group size of Psycho Pig decreased."""
            ),
            ToWikiModVersion(
                code = "u3",
                name = "Update 3",
                mcVersion = "1.7.2",
                commit = "a37c4a595f58d53c912baf6a15c843f40a95487f",
                released = ZonedDateTime.of(2015, 2, 22, 19, 24, 51, 0, ZoneId.of("Europe/London")),
                changelog = """Changed nuke mechanics, wasteland spawn rate reduced, added psychomeat."""
            ),
            ToWikiModVersion(
                code = "u3.1",
                name = "Update 3.1",
                mcVersion = "1.7.2",
                commit = "14a72db329c665199e10a42c30c4f7cdd1350385",
                released = ZonedDateTime.of(2015, 2, 22, 20, 41, 1, 0, ZoneId.of("Europe/London")),
                changelog = """Reworked nuclear missile model, plans to add more missile strikes."""
            ),
            ToWikiModVersion(
                code = "u4",
                name = "Update 4",
                mcVersion = "1.7.2",
                commit = "513607b7b7d22e95c4c529938990690bca73d9e4",
                released = ZonedDateTime.of(2015, 2, 28, 11, 31, 57, 0, ZoneId.of("Europe/London")),
                changelog = """Pills almost work, Missile Launch Pads render properly with GUI, Oil Buckets."""
            ),
            ToWikiModVersion(
                code = "u5",
                name = "Update 5",
                mcVersion = "1.7.2",
                commit = "ce3555dd996a3425c2e2372a5f408eb8c9156e07",
                released = ZonedDateTime.of(2015, 3, 1, 20, 32, 8, 0, ZoneId.of("Europe/London")),
                changelog = """Adding computer functionality, new parts including motherboard."""
            ),
            ToWikiModVersion(
                code = "u6",
                name = "Update 6",
                mcVersion = "1.7.2",
                commit = "2c88ed0c20e07d946e450d1f5521983342cf4692",
                released = ZonedDateTime.of(2015, 3, 8, 10, 45, 50, 0, ZoneId.of("Europe/London")),
                changelog = """Updated graphics, hiatus on missiles and computers, new mobs in the works."""
            ),
            ToWikiModVersion(
                code = "u7",
                name = "Update 7",
                mcVersion = "1.7.2",
                commit = "b2710e0a0c54ef8047f3682fd2e180d923755006",
                released = ZonedDateTime.of(2015, 3, 27, 7, 55, 17, 0, ZoneId.of("Europe/London")),
                changelog = """Added Aerstone with different subtypes, added Sparkling Dragon, added rope blocks."""
            ),
            ToWikiModVersion(
                code = "u8",
                name = "Update 8",
                mcVersion = "1.7.2",
                commit = "468d7c9c9bac3c8a41a1fb8bdbe75534eed5747c",
                released = ZonedDateTime.of(2015, 12, 19, 21, 10, 14, 0, ZoneId.of("Europe/London")),
                changelog = """Added more potion effects and pill effects, added luck stat, fixed infinite arrow bug with aerstone repeater."""
            ),
            ToWikiModVersion(
                code = "u9",
                name = "Update 9",
                mcVersion = "1.7.2",
                commit = "bf606df7f07268a44010e4961305f4256b7908ad",
                released = ZonedDateTime.of(2015, 12, 22, 16, 44, 20, 0, ZoneId.of("Europe/London")),
                changelog = """Added 2 mobs and bloodwood to the wasteland, new light affinity biome WIP, fly swatter & poop harvester, nod to kyle, hellfire. poison spikes."""
            ),
            ToWikiModVersion(
                code = "u9.1",
                name = "Update 9.1",
                mcVersion = "1.7.2",
                commit = "30d19b049b5cdd56ead32854553231995980d050",
                released = ZonedDateTime.of(2015, 12, 27, 18, 47, 50, 0, ZoneId.of("Europe/London")),
                changelog = """Quick nerf to hellfire."""
            ),
            ToWikiModVersion(
                code = "u9.2",
                name = "Update 9.2",
                mcVersion = "1.7.2",
                commit = "837356d979e71927b9694d9785f49413dbd9fc9e",
                released = ZonedDateTime.of(2015, 12, 27, 21, 57, 40, 0, ZoneId.of("Europe/London")),
                changelog = """Texture change to light leaves, classic sounds fixed."""
            ),
            ToWikiModVersion(
                code = "u10",
                name = "Update 10",
                mcVersion = "1.7.2",
                commit = "b9c7f0f69bf87a7c3bf6fa5fdc7058f87085d891",
                released = ZonedDateTime.of(2016, 1, 10, 16, 44, 13, 0, ZoneId.of("Europe/London")),
                changelog = """Light aura now contains light stone right off the bat, custom saplings and leaves now work, mobs die and leave light shards in light aura."""
            ),
            ToWikiModVersion(
                code = "u11",
                name = "Update 11",
                mcVersion = "1.7.2",
                commit = "29c59f9e2c758d864d874d93c1a8b5a6d0933c6d",
                released = ZonedDateTime.of(2016, 1, 23, 17, 14, 16, 0, ZoneId.of("Europe/London")),
                changelog = """Light biomes no longer emit light due to lighting issues, different spawn eggs for different types of mobs (no longer limited to rainbow spawn eggs), work on winged creeper."""
            ),
            ToWikiModVersion(
                code = "u11.1",
                name = "Update 11.1",
                mcVersion = "1.7.2",
                commit = "3d198e93045f37f826cbcb37d1ee1e42515da0c8",
                released = ZonedDateTime.of(2016, 1, 23, 20, 19, 36, 0, ZoneId.of("Europe/London")),
                changelog = """Added aura conversion enum system."""
            ),
            ToWikiModVersion(
                code = "u12",
                name = "Update 12",
                mcVersion = "1.7.2",
                commit = "d511299d953ad1703abb41e382730b78359a30d4",
                released = ZonedDateTime.of(2016, 1, 24, 17, 48, 48, 0, ZoneId.of("Europe/London")),
                changelog = """Added more light blocks. Changed some light block textures."""
            ),
        ))

    override val mods by lazy { listOf(toBase, lcc, lccForge, aimagg, yam) }
    override val builds by lazy { mods.filter { it.legacy }.flatMap { it.generateBuilds() } }

}
