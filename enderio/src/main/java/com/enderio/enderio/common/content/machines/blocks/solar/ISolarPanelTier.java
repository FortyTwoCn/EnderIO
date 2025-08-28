package com.enderio.enderio.common.content.machines.blocks.solar;

import com.enderio.enderio.common.content.machines.base.multienergy.CapacityTier;

// TODO: I want to drop the I from all our interfaces. This one has a name conflict.
public interface ISolarPanelTier extends CapacityTier {
    int getProductionRate();
}
