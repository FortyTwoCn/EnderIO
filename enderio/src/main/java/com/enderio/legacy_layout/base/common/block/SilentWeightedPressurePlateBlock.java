package com.enderio.legacy_layout.base.common.block;

import com.enderio.enderio.common.foundation.block.EIOBlockSetType;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;

public class SilentWeightedPressurePlateBlock extends WeightedPressurePlateBlock {

    public SilentWeightedPressurePlateBlock(WeightedPressurePlateBlock from) {
        super(from.maxWeight, EIOBlockSetType.SILENT, Properties.ofFullCopy(from));
    }

}
