package com.enderio.enderio.common.registration.legacy_regilite.armory;

import com.enderio.legacy_layout.armory.EnderIOArmory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;

public class ArmoryFeatureFlags {
    public static final FeatureFlag ARMORY_REWRITE = FeatureFlags.REGISTRY
            .getFlag(ResourceLocation.fromNamespaceAndPath(EnderIOArmory.MODULE_MOD_ID, "armory_rewrite"));
}
