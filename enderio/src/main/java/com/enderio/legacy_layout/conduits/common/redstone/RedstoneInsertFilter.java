package com.enderio.legacy_layout.conduits.common.redstone;

import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduitNetworkContext;
import net.minecraft.world.item.DyeColor;

public interface RedstoneInsertFilter {

    int getOutputSignal(RedstoneConduitNetworkContext context, DyeColor control);
}
