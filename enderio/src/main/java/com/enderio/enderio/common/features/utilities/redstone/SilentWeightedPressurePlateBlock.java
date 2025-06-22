package com.enderio.enderio.common.features.utilities.redstone;

import com.enderio.enderio.common.legacy_to_move.blocks.EIOBlockSetType;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;

public class SilentWeightedPressurePlateBlock extends WeightedPressurePlateBlock {

    public SilentWeightedPressurePlateBlock(WeightedPressurePlateBlock from) {
        super(from.maxWeight, EIOBlockSetType.SILENT, Properties.ofFullCopy(from));
    }

}
