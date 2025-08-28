package com.enderio.enderio.common.compat.cc_tweaked;

import com.enderio.legacy_layout.base.api.integration.Integration;
import dan200.computercraft.api.ComputerCraftAPI;
import net.neoforged.bus.api.IEventBus;

public class CCIntegration implements Integration {

    @Override
    public void addEventListener(IEventBus modEventBus, IEventBus forgeEventBus) {
        ComputerCraftAPI.registerBundledRedstoneProvider(new EIOBundledRedstoneProvider());
    }
}
