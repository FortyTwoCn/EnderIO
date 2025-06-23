package com.enderio.enderio.common.foundation.network.menu;

import java.util.List;

public interface ContainerSyncData {
    List<SyncSlot> syncSlots();

    default List<SyncSlot> updatableSyncSlots() {
        return List.of();
    }
}
