package com.enderio.enderio.common.content.machines.base.task;

import com.enderio.legacy_layout.machines.common.io.energy.IMachineEnergyStorage;

public interface PoweredMachineTask extends MachineTask {
    IMachineEnergyStorage getEnergyStorage();
}
