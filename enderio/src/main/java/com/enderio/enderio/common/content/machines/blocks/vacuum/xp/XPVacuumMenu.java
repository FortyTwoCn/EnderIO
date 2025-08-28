package com.enderio.enderio.common.content.machines.blocks.vacuum.xp;

import com.enderio.enderio.common.content.machines.base.fluid.FluidStorageInfo;
import com.enderio.enderio.common.content.machines.base.fluid.FluidStorageSyncSlot;
import com.enderio.enderio.common.content.machines.blocks.vacuum.VacuumMenu;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlockEntities;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineMenus;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class XPVacuumMenu extends VacuumMenu<XPVacuumBlockEntity> {

    private final FluidStorageSyncSlot fluidTankSlot;

    public XPVacuumMenu(int containerId, Inventory inventory, XPVacuumBlockEntity blockEntity) {
        super(MachineMenus.XP_VACUUM.get(), containerId, inventory, blockEntity);
        addSlots();

        fluidTankSlot = addSyncSlot(
                FluidStorageSyncSlot.readOnly(() -> FluidStorageInfo.of(blockEntity.getFluidTank())));
    }

    public XPVacuumMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf buf) {
        super(MachineMenus.XP_VACUUM.get(), containerId, playerInventory, buf, MachineBlockEntities.XP_VACUUM.get());
        addSlots();

        fluidTankSlot = addSyncSlot(FluidStorageSyncSlot.standalone());
    }

    private void addSlots() {
        addPlayerInventorySlots(8, 70);
    }

    public FluidStorageInfo getFluidTank() {
        return fluidTankSlot.get();
    }
}
