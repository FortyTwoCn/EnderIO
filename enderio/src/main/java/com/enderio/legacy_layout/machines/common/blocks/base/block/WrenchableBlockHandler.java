package com.enderio.legacy_layout.machines.common.blocks.base.block;

import com.enderio.legacy_layout.base.common.blockentity.Wrenchable;
import com.enderio.enderio.common.registration.EIOTags;
import com.enderio.legacy_layout.machines.EnderIOMachines;
import net.minecraft.world.ItemInteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = EnderIOMachines.MODULE_MOD_ID)
public class WrenchableBlockHandler {
    @SubscribeEvent
    public static void onItemUse(UseItemOnBlockEvent event) {
        var level = event.getLevel();

        if (!event.getItemStack().is(EIOTags.Items.WRENCH)) {
            return;
        }

        if (level.getBlockEntity(event.getPos()) instanceof Wrenchable blockEntity) {
            var result = blockEntity.onWrenched(event.getUseOnContext());
            if (result != ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION) {
                event.cancelWithResult(result);
            }
        }
    }
}
