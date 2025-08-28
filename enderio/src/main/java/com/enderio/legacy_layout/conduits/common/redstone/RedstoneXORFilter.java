package com.enderio.legacy_layout.conduits.common.redstone;

import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduitNetworkContext;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public class RedstoneXORFilter extends DoubleRedstoneChannel implements RedstoneInsertFilter {

    public RedstoneXORFilter(ItemStack stack) {
        super(stack, ConduitComponents.REDSTONE_XOR_FILTER);
    }

    @Override
    public int getOutputSignal(RedstoneConduitNetworkContext context, DyeColor control) {
        boolean b = context.isActive(getFirstChannel()) ^ context.isActive(getSecondChannel());
        return b ? 15 : 0;
    }
}
