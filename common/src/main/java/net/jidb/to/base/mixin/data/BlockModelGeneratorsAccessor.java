package net.jidb.to.base.mixin.data;

import com.google.gson.JsonElement;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(BlockModelGenerators.class)
public interface BlockModelGeneratorsAccessor {
    @Accessor
    Consumer<BlockStateGenerator> getBlockStateOutput();

    @Accessor
    BiConsumer<ResourceLocation, Supplier<JsonElement>> getModelOutput();
}
