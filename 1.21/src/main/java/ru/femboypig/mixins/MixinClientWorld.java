package ru.femboypig.mixins;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import ru.femboypig.config.BrushCC;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld extends World {

    protected MixinClientWorld(MutableWorldProperties properties, RegistryKey<World> registryRef, DynamicRegistryManager registryManager, RegistryEntry<DimensionType> dimensionEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates) {
        super(properties, registryRef, registryManager, dimensionEntry, profiler, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
    }

    @Override
    public float getRainGradient(float delta) {
        return BrushCC.CONFIG.instance().noWeather ? 0 : super.getRainGradient(delta);
    }

    @Override
    public float getThunderGradient(float delta) {
        return BrushCC.CONFIG.instance().noWeather ? 0 : super.getThunderGradient(delta);
    }

}