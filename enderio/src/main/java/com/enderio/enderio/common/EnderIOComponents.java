package com.enderio.enderio.common;

import net.minecraft.network.chat.Component;

public class EnderIOComponents {

    // TODO: Add common EIO lang components here.

    public static Component translatable(String key) {
        return Component.translatable(EnderIO.MOD_ID + "." + key);
    }
}
