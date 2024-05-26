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
        boolean exit = System.getProperty("net.jidb.to.base.data.ci") != null;
        if (exit) {
            System.exit(0);
        } else {
            System.out.println("Data generation complete. Pass a property to net.jidb.to.base.data.ci to automatically close next run.");
        }
    }

}
