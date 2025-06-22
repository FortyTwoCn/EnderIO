package com.enderio.enderio.common;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.EnderIORegistries;
import com.enderio.enderio.common.features.equipment.glider.PlayerMovementHandler;
import com.enderio.enderio.common.legacy_to_move.config.BaseConfig;
import com.enderio.enderio.common.legacy_to_move.config.BaseConfigLang;
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
import com.enderio.enderio.tempdata.EIODataProvider;
import com.enderio.enderio.tempdata.advancement.EIOAdvancementGenerator;
import com.enderio.enderio.tempdata.loot.ChestLootProvider;
import com.enderio.enderio.tempdata.loot.EIOLootModifiersProvider;
import com.enderio.enderio.tempdata.recipe.BlockRecipeProvider;
import com.enderio.enderio.tempdata.recipe.FilterRecipeProvider;
import com.enderio.enderio.tempdata.recipe.FireCraftingRecipeProvider;
import com.enderio.enderio.tempdata.recipe.GlassRecipeProvider;
import com.enderio.enderio.tempdata.recipe.ItemRecipeProvider;
import com.enderio.enderio.tempdata.recipe.MaterialRecipeProvider;
import com.enderio.enderio.tempdata.tags.EIOBlockTagsProvider;
import com.enderio.enderio.tempdata.tags.EIOEntityTagsProvider;
import com.enderio.enderio.tempdata.tags.EIOFluidTagsProvider;
import com.enderio.enderio.tempdata.tags.EIOItemTagsProvider;
import com.enderio.regilite.Regilite;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        modEventBus.addListener(EventPriority.LOWEST, this::onGatherData);

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

    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        EIODataProvider provider = new EIODataProvider("base");

        provider.addSubProvider(event.includeServer(), new MaterialRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new BlockRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new ItemRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new GlassRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new FireCraftingRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new FilterRecipeProvider(packOutput, lookupProvider));
        provider.addSubProvider(event.includeServer(), new EIOLootModifiersProvider(packOutput, lookupProvider));

        var b = new EIOBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        provider.addSubProvider(event.includeServer(), b);
        provider.addSubProvider(event.includeServer(),
            new EIOItemTagsProvider(packOutput, lookupProvider, b.contentsGetter(), existingFileHelper));
        provider.addSubProvider(event.includeServer(),
            new EIOFluidTagsProvider(packOutput, lookupProvider, existingFileHelper));
        provider.addSubProvider(event.includeServer(),
            new EIOEntityTagsProvider(packOutput, lookupProvider, existingFileHelper));
        provider.addSubProvider(event.includeServer(), new AdvancementProvider(packOutput, lookupProvider,
            existingFileHelper, List.of(new EIOAdvancementGenerator())));
        provider.addSubProvider(event.includeServer(),
            new LootTableProvider(packOutput, Collections.emptySet(), List
                .of(new LootTableProvider.SubProviderEntry(ChestLootProvider::new, LootContextParamSets.CHEST)),
                lookupProvider));
        generator.addProvider(true, provider);
    }
}
