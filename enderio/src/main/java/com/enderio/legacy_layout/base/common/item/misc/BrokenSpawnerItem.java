package com.enderio.legacy_layout.base.common.item.misc;

import com.enderio.enderio.api.soul.Soul;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIODataComponents;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOItems;
import com.enderio.enderio.common.foundation.utility.EntityCaptureUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BrokenSpawnerItem extends Item {
    public BrokenSpawnerItem(Properties pProperties) {
        super(pProperties);
    }

    public static ItemStack forSoul(Soul soul) {
        ItemStack brokenSpawner = new ItemStack(EIOItems.BROKEN_SPAWNER.get());
        brokenSpawner.set(EIODataComponents.SOUL, soul);
        return brokenSpawner;
    }

    public static List<ItemStack> getPossibleStacks() {
        // Register for every mob that can be captured.
        List<ItemStack> items = new ArrayList<>();
        for (var entity : EntityCaptureUtils.getCapturableEntityTypes()) {
            items.add(forSoul(Soul.of(entity)));
        }
        return items;
    }
}
