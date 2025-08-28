package com.enderio.enderio.common.content.filters.soul;

import com.enderio.enderio.api.soul.Soul;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOCapabilities;
import com.enderio.enderio.common.content.filters.FilterSlot;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.item.ItemStack;

public class SoulFilterSlot extends FilterSlot<Soul> {

    public SoulFilterSlot(Supplier<Soul> getter, Consumer<Soul> setter, int pSlot, int pX, int pY) {
        super(getter, setter, pSlot, pX, pY);
    }

    @Override
    protected Soul emptyResource() {
        return Soul.EMPTY;
    }

    @Override
    public Optional<Soul> getResourceFrom(ItemStack itemStack) {
        var soulBindable = itemStack.getCapability(EIOCapabilities.SoulBindable.ITEM);
        if (soulBindable != null) {
            return Optional.of(soulBindable.getBoundSoul());
        }

        return Optional.empty();
    }
}
