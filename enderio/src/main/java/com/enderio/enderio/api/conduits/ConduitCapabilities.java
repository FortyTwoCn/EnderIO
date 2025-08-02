package com.enderio.enderio.api.conduits;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.conduits.facade.ConduitFacadeProvider;
import com.enderio.legacy_layout.conduits.common.redstone.RedstoneExtractFilter;
import com.enderio.legacy_layout.conduits.common.redstone.RedstoneInsertFilter;
import net.neoforged.neoforge.capabilities.ItemCapability;

public class ConduitCapabilities {
    public static final ItemCapability<ConduitFacadeProvider, Void> CONDUIT_FACADE_PROVIDER = ItemCapability
            .createVoid(EnderIOAPI.loc("conduit_facade_provider"), ConduitFacadeProvider.class);

    public static final ItemCapability<RedstoneInsertFilter, Void> REDSTONE_INSERT_FILTER = ItemCapability
            .createVoid(EnderIOAPI.loc("redstone_insert_filter"), RedstoneInsertFilter.class);

    public static final ItemCapability<RedstoneExtractFilter, Void> REDSTONE_EXTRACT_FILTER = ItemCapability
            .createVoid(EnderIOAPI.loc("redstone_extract_filter"), RedstoneExtractFilter.class);
}
