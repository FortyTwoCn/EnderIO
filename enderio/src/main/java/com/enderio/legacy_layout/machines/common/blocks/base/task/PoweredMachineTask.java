package com.enderio.legacy_layout.machines.common.blocks.base.task;

import com.enderio.legacy_layout.machines.common.io.energy.IMachineEnergyStorage;

public interface PoweredMachineTask extends MachineTask {
    IMachineEnergyStorage getEnergyStorage();
}
