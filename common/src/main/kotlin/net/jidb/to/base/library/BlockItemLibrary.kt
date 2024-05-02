package net.jidb.to.base.library

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

open class BlockItemLibrary(modid: String, private val getter: () -> Map<String, Block>) : SimpleLibrary<BlockItem?>(modid) {

    constructor(modid: String, library: Library<*, out Block>) : this(modid, { library.values })
    constructor(modid: String, blocks: Map<String, Block>) : this(modid, { blocks })

    val registry = DeferredRegister.create(modid, Registries.ITEM)

    private val blocks by lazy(getter)

    override fun afterBuild() {
        for ((name, block) in blocks) {
            val entry = this.entries[name]
            val blockitem = (if (entry != null) entry.value else createDefault(block, name)) ?: continue
            registry.register(name) { blockitem }
        }
        registry.register()
    }

    open fun createDefault(block: Block, name: String): BlockItem? = BlockItem(block, Item.Properties())

}