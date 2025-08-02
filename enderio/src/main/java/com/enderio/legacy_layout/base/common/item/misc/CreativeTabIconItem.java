package com.enderio.legacy_layout.base.common.item.misc;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CreativeTabIconItem extends Item {
    public CreativeTabIconItem(Properties props) {
        super(props);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
}
