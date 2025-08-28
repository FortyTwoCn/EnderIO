package com.enderio.enderio.common.content.machines.legacy;

import net.minecraft.core.BlockPos;

import java.util.List;

// TODO: marked as legacy because this might need a rethink?
public interface MultiConfigurable {
    List<BlockPos> getConfigurables();
}
