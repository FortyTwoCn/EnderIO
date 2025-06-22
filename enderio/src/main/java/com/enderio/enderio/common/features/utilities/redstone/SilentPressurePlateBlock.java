package com.enderio.enderio.common.features.utilities.redstone;

import com.enderio.enderio.common.legacy_to_move.blocks.EIOBlockSetType;
import net.minecraft.world.level.block.PressurePlateBlock;

public class SilentPressurePlateBlock extends PressurePlateBlock {

    public SilentPressurePlateBlock(PressurePlateBlock wrapped) {
        super(EIOBlockSetType.SILENT, Properties.ofFullCopy(wrapped));
    }

}
