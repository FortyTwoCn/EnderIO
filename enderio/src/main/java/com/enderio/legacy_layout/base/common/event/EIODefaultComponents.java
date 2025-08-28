package com.enderio.legacy_layout.base.common.event;

import com.enderio.enderio.common.EnderIO;
import com.enderio.legacy_layout.base.api.grindingball.GrindingBallData;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIODataComponents;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = EnderIO.MOD_ID)
public class EIODefaultComponents {
    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.FLINT, i -> i.set(EIODataComponents.GRINDING_BALL.get(),
            new GrindingBallData(1.2F, 1.25F, 0.85F, 24000)));
    }
}
