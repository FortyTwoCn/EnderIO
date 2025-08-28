package com.enderio.enderio.common.content.decoration;

import com.enderio.enderio.common.foundation.block.EIOBlockSetType;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;

public class SilentWeightedPressurePlateBlock extends WeightedPressurePlateBlock {

    public SilentWeightedPressurePlateBlock(WeightedPressurePlateBlock from) {
        super(from.maxWeight, EIOBlockSetType.SILENT, Properties.ofFullCopy(from));
    }

}
