package ru.femboypig.mixins;

import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBrandRetriever.class)
public abstract class MixinClientBrandRetriever {
    @Inject(method = "getClientModName", at = @At("HEAD"), cancellable = true, remap = false)
    private static void returnClientBrand(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Brush Client");
        cir.cancel();
    }
}