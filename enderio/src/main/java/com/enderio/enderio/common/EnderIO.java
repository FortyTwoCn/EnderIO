package com.enderio.enderio.common;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.EnderIORegistries;
import com.enderio.enderio.common.features.equipment.glider.PlayerMovementHandler;
import com.enderio.enderio.common.legacy_to_move.config.base.BaseConfig;
import com.enderio.enderio.common.legacy_to_move.config.base.BaseConfigLang;
import com.enderio.enderio.common.legacy_to_move.integrations.Integrations;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOAttachments;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOBlockEntities;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOBlocks;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOCreativeTabs;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOCriterions;
import com.enderio.enderio.common.registration.legacy_modules.base.EIODataComponents;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOEntities;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOEnumLang;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOFluids;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOIngredientTypes;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOItems;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOLang;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOLootModifiers;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOMenus;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOParticles;
import com.enderio.enderio.common.registration.legacy_modules.base.EIORecipes;
import com.enderio.regilite.Regilite;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.io.IOException;
import java.nio.file.Files;

@Mod(EnderIO.MOD_ID)
public class EnderIO {
    public static final String MOD_ID = EnderIOAPI.MOD_ID;

    // TODO: We want to slowly abandon Regilite
    @Deprecated(since = "8.0.0")
    public static final Regilite REGILITE = new Regilite(MOD_ID);

    // TODO: remove when Integrations are gone.
    public static IEventBus modEventBus;

    @Deprecated(forRemoval = true)
    public static ResourceLocation loc(String path) {
        return EnderIOAPI.rl(path);
    }

    public static ResourceLocation rl(String path) {
        return EnderIOAPI.rl(path);
    }

    public EnderIO(IEventBus eventBus, ModContainer modContainer) {
        EnderIO.modEventBus = eventBus;

        // Ensure the enderio config subdirectory is present.
        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(EnderIO.MOD_ID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register config files
        modContainer.registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON_SPEC, "enderio/base-common.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_SPEC, "enderio/base-client.toml");
        BaseConfigLang.register();

        // Register all the things!
        EIODataComponents.register(modEventBus);
        EIOCreativeTabs.register(modEventBus);
        EIOItems.register(modEventBus);
        EIOBlocks.register(modEventBus);
        EIOBlockEntities.register(modEventBus);
        EIOFluids.register(modEventBus);
        EIOMenus.register(modEventBus);
        EIOLang.register();
        EIOEnumLang.register();
        EIORecipes.register(modEventBus);
        EIOLootModifiers.register(modEventBus);
        EIOParticles.register(modEventBus);
        EIOEntities.register(modEventBus);
        EIOAttachments.register(modEventBus);
        EIOCriterions.register(modEventBus);
        EIOIngredientTypes.register(modEventBus);
        REGILITE.register(modEventBus);

        // Register event handlers
        eventBus.addListener(this::registerRegistries);
        eventBus.addListener(this::sendIMC);

        NeoForge.EVENT_BUS.addListener(PlayerMovementHandler::onPlayerTick);

        // Integrations
        Integrations.register();
    }

    private void registerRegistries(NewRegistryEvent event) {
        event.register(EnderIORegistries.TRAVEL_TARGET_TYPES);
        event.register(EnderIORegistries.TRAVEL_TARGET_SERIALIZERS);
    }

    private void sendIMC(InterModEnqueueEvent event) {
//        InterModComms.sendTo("inventorysorter", "slotblacklist", ItemFilterSlot.class::getName);
//        InterModComms.sendTo("inventorysorter", "slotblacklist", FluidFilterSlot.class::getName);
    }
}
