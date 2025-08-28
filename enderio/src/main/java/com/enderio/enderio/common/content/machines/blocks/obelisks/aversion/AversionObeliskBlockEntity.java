package com.enderio.enderio.common.content.machines.blocks.obelisks.aversion;

import com.enderio.enderio.api.capacitor.CapacitorModifier;
import com.enderio.enderio.api.capacitor.LinearScalable;
import com.enderio.enderio.api.capacitor.QuadraticScalable;
import com.enderio.legacy_layout.base.api.filter.SoulFilter;
import com.enderio.legacy_layout.base.api.io.energy.EnergyIOMode;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOCapabilities;
import com.enderio.enderio.common.content.machines.base.blockentity.flags.CapacitorSupport;
import com.enderio.enderio.common.content.machines.base.inventory.MachineInventoryLayout;
import com.enderio.enderio.common.content.machines.blocks.obelisks.ObeliskBlockEntity;
import com.enderio.legacy_layout.machines.common.config.MachinesConfig;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlockEntities;
import com.enderio.legacy_layout.machines.common.obelisk.AversionObeliskManager;
import com.enderio.legacy_layout.machines.common.obelisk.ObeliskAreaManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import org.jetbrains.annotations.Nullable;

public class AversionObeliskBlockEntity extends ObeliskBlockEntity<AversionObeliskBlockEntity> {

    private static final QuadraticScalable ENERGY_CAPACITY = new QuadraticScalable(CapacitorModifier.ENERGY_CAPACITY,
            MachinesConfig.COMMON.ENERGY.AVERSION_CAPACITY);
    private static final LinearScalable ENERGY_USAGE = new LinearScalable(CapacitorModifier.ENERGY_USE,
            MachinesConfig.COMMON.ENERGY.AVERSION_USAGE);
    private static final LinearScalable RANGE = new LinearScalable(CapacitorModifier.ENERGY_USE,
            MachinesConfig.COMMON.AVERSION_RANGE);

    public AversionObeliskBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MachineBlockEntities.AVERSION_OBELISK.get(), worldPosition, blockState, false, CapacitorSupport.REQUIRED,
                EnergyIOMode.Input, ENERGY_CAPACITY, ENERGY_USAGE);
    }

    @Override
    protected @Nullable ObeliskAreaManager<AversionObeliskBlockEntity> getAreaManager(ServerLevel level) {
        return AversionObeliskManager.getManager(level);
    }

    @Override
    public @Nullable MachineInventoryLayout createInventoryLayout() {
        return MachineInventoryLayout.builder()
                .inputSlot((integer, itemStack) -> itemStack.getCapability(EIOCapabilities.SOUL_FILTER) != null)
                .slotAccess(FILTER)
                .capacitor()
                .build();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AversionObeliskMenu(containerId, pPlayerInventory, this);
    }

    @Override
    public int getMaxRange() {
        return RANGE.scaleI(this::getCapacitorData).get();
    }

    @Override
    public String getColor() {
        return MachinesConfig.CLIENT.BLOCKS.AVERSION_RANGE_COLOR.get();
    }

    public boolean handleSpawnEvent(FinalizeSpawnEvent event) {
        if (!isActive() || getAABB() == null) {
            return false;
        }
        SoulFilter filter = getSoulFilter();
        if (filter == null || !filter.test(event.getEntity())) {
            return false;
        }
        if (getAABB().contains(event.getX(), event.getY(), event.getZ())) {
            event.setSpawnCancelled(true);
            return true;
        }
        return false;
    }
}
