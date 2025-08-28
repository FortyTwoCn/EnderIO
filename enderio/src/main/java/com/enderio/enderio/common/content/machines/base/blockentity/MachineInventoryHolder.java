package com.enderio.enderio.common.content.machines.base.blockentity;

import com.enderio.enderio.common.content.machines.base.inventory.MachineInventory;

public interface MachineInventoryHolder {
    boolean hasInventory();

    MachineInventory getInventory();
}
