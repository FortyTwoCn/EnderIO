package com.enderio.enderio.common.foundation;

import com.enderio.enderio.common.EnderIO;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = EnderIO.MOD_ID, value = Dist.CLIENT)
public class AlphaWarning {
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().sendSystemMessage(Component
            .literal("[Warning] Ender IO is in alpha - expect bugs and take backups often!")
            .withStyle(ChatFormatting.YELLOW));
    }
}
