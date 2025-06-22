package com.enderio.enderio.api;

import net.minecraft.resources.ResourceLocation;

public class EnderIOAPI {
    public static final String MOD_ID = "enderio";

    @Deprecated(forRemoval = true)
    public static final String NAMESPACE = MOD_ID;

    @Deprecated(forRemoval = true)
    public static ResourceLocation loc(String path) {
        return rl(path);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, path);
    }
}
