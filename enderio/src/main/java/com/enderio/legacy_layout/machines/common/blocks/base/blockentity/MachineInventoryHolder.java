package com.enderio.legacy_layout.machines.common.blocks.base.blockentity;

import com.enderio.legacy_layout.machines.common.blocks.base.inventory.MachineInventory;

public interface MachineInventoryHolder {
    boolean hasInventory();

    MachineInventory getInventory();
}
