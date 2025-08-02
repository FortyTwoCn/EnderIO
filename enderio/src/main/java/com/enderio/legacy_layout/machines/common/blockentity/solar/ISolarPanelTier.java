package com.enderio.legacy_layout.machines.common.blockentity.solar;

import com.enderio.legacy_layout.machines.common.blockentity.multienergy.CapacityTier;

// TODO: I want to drop the I from all our interfaces. This one has a name conflict.
public interface ISolarPanelTier extends CapacityTier {
    int getProductionRate();
}
