package com.enderio.enderio.common.foundation.network.menu;

import com.enderio.enderio.common.foundation.network.menu.payload.SlotPayload;
import net.minecraft.world.level.Level;

/**
 * Inspired by Mekanism's SyncableData system.
 */
public interface SyncSlot {
    ChangeType detectChanges();

    SlotPayload createPayload(Level level, ChangeType changeType);

    void unpackPayload(Level level, SlotPayload payload);

    enum ChangeType {
        NONE, PARTIAL, FULL
    }
}
