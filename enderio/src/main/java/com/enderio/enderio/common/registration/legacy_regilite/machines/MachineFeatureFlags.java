package com.enderio.enderio.common.registration.legacy_regilite.machines;

import com.enderio.enderio.common.EnderIO;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;

public class MachineFeatureFlags {
    public static final FeatureFlag FARMING_STATION = FeatureFlags.REGISTRY
            .getFlag(ResourceLocation.fromNamespaceAndPath(EnderIO.MOD_ID, "farming_station"));

    public static final FeatureFlag ENDERFACE = FeatureFlags.REGISTRY
            .getFlag(ResourceLocation.fromNamespaceAndPath(EnderIO.MOD_ID, "enderface"));
}
