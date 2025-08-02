package com.enderio.legacy_layout.conduits.common.redstone;

import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduitNetworkContext;
import com.enderio.legacy_layout.conduits.common.init.ConduitComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public class RedstoneANDFilter extends DoubleRedstoneChannel implements RedstoneInsertFilter {

    public RedstoneANDFilter(ItemStack stack) {
        super(stack, ConduitComponents.REDSTONE_AND_FILTER);
    }

    @Override
    public int getOutputSignal(RedstoneConduitNetworkContext context, DyeColor control) {
        boolean b = context.isActive(getFirstChannel()) && context.isActive(getSecondChannel());
        return b ? 15 : 0;
    }
}
