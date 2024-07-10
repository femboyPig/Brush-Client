package ru.femboypig.mixins;

import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.femboypig.config.BrushCC;

import static ru.femboypig.utils.interfaces.instance.mc;

@Mixin(value = {TextVisitFactory.class})
public class MixinTextVisitFactory {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z", ordinal = 0), method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z" }, index = 0)
    private static String adjustText(String text) {
        return protect(text);
    }

    private static String protect(String string) {
        if (!BrushCC.CONFIG.instance().hideName || mc.player == null)
            return string;
        String me = mc.getSession().getUsername();
        if (string.contains(me))
            return string.replace(me, BrushCC.CONFIG.instance().fakeName);
        return string;
    }
}