package net.jidb.to.base.fabric.mixin.data;

import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FabricDataGenHelper.class, remap = false)
public abstract class FabricDataGenHelperMixin {

    @Inject(method = "runInternal()V", at = @At("TAIL"))
    private static void exit(CallbackInfo ci) {
        boolean exit = System.getenv("NET_JIDB_TO_BASE_DATA_CI") != null;
        if (exit) {
            System.exit(0);
        } else {
            System.out.println("Data generation complete. Define environment variable NET_JIDB_TO_BASE_DATA_CI to automatically close next run.");
        }
    }

}
