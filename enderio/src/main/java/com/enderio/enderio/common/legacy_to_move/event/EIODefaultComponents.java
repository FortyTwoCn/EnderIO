package com.enderio.enderio.common.legacy_to_move.event;

import com.enderio.enderio.api.grindingball.GrindingBallData;
import com.enderio.enderio.common.registration.legacy_modules.base.EIODataComponents;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber
public class EIODefaultComponents {
    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.FLINT, i -> i.set(EIODataComponents.GRINDING_BALL.get(),
            new GrindingBallData(1.2F, 1.25F, 0.85F, 24000)));
    }
}
