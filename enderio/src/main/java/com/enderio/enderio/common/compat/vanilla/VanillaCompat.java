package com.enderio.enderio.common.compat.vanilla;

import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.api.soul.binding.ISoulBindable;
import com.enderio.enderio.api.soul.storage.ISoulHandler;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOCapabilities;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = EnderIO.MOD_ID)
public class VanillaCompat {

    public static final ICapabilityProvider<ItemStack, Void, ISoulBindable> SPAWN_EGG_BINDABLE_PROVIDER =
        (stack, v) -> new SpawnEggSoulBindable(stack);

    public static final ICapabilityProvider<ItemStack, Void, ISoulHandler> SPAWN_EGG_HANDLER_PROVIDER =
        (stack, v) -> new SpawnEggSoulHandler(stack);

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        for (var spawnEgg : SpawnEggItem.eggs()) {
            event.registerItem(EIOCapabilities.SoulBindable.ITEM, SPAWN_EGG_BINDABLE_PROVIDER, spawnEgg);
            event.registerItem(EIOCapabilities.SoulHandler.ITEM, SPAWN_EGG_HANDLER_PROVIDER, spawnEgg);
        }
    }
}
