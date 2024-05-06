package net.jidb.to.base.mixin.data;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

@Mixin(TexturedModel.class)
public interface TexturedModelAccessor {
    @Invoker("createDefault")
    static TexturedModel.Provider createDefault(Function<Block, TextureMapping> function, ModelTemplate modelTemplate) {
        throw new AssertionError();
    }
}