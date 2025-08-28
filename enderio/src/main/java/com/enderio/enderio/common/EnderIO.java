package com.enderio.enderio.common;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.conduits.Conduit;
import com.enderio.enderio.common.registration.EIONetworkPackets;
import com.enderio.enderio.api.EnderIORegistries;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineAttachments;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlockEntities;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineDataComponents;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineMenus;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineRecipes;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineTravelTargets;
import com.enderio.legacy_layout.base.api.integration.IntegrationManager;
import com.enderio.legacy_layout.base.common.config.BaseConfig;
import com.enderio.legacy_layout.base.common.config.BaseConfigLang;
import com.enderio.enderio.common.content.filters.fluid.FluidFilterSlot;
import com.enderio.enderio.common.content.filters.item.ItemFilterSlot;
import com.enderio.enderio.common.content.utility.glider.PlayerMovementHandler;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOAttachments;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOBlockEntities;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOCreativeTabs;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOCriterions;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIODataComponents;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOEntities;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOFluids;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOIngredientTypes;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOItems;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOLootModifiers;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOMenus;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOParticles;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIORecipes;
import com.enderio.legacy_layout.base.common.integrations.Integrations;
import com.enderio.legacy_layout.base.common.item.tool.SoulVialItem;
import com.enderio.legacy_layout.base.common.lang.EIOEnumLang;
import com.enderio.legacy_layout.base.common.lang.EIOLang;
import com.enderio.enderio.common.registration.EIOTags;
import com.enderio.legacy_layout.base.data.EIODataProvider;
import com.enderio.legacy_layout.base.data.advancement.EIOAdvancementGenerator;
import com.enderio.legacy_layout.base.data.loot.ChestLootProvider;
import com.enderio.legacy_layout.base.data.loot.EIOLootModifiersProvider;
import com.enderio.legacy_layout.base.data.recipe.BlockRecipeProvider;
import com.enderio.legacy_layout.base.data.recipe.FilterRecipeProvider;
import com.enderio.legacy_layout.base.data.recipe.FireCraftingRecipeProvider;
import com.enderio.legacy_layout.base.data.recipe.GlassRecipeProvider;
import com.enderio.legacy_layout.base.data.recipe.ItemRecipeProvider;
import com.enderio.legacy_layout.base.data.recipe.MaterialRecipeProvider;
import com.enderio.legacy_layout.base.data.tags.EIOBlockTagsProvider;
import com.enderio.legacy_layout.base.data.tags.EIOEntityTagsProvider;
import com.enderio.legacy_layout.base.data.tags.EIOFluidTagsProvider;
import com.enderio.legacy_layout.base.data.tags.EIOItemTagsProvider;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitBlockEntities;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitComponents;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitIngredientTypes;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitItems;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitLang;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitMenus;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitTypes;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.Conduits;
import com.enderio.legacy_layout.conduits.data.ConduitTagProvider;
import com.enderio.legacy_layout.conduits.data.recipe.ConduitRecipes;
import com.enderio.legacy_layout.machines.common.blocks.base.menu.GhostMachineSlot;
import com.enderio.legacy_layout.machines.common.blocks.base.menu.MachineSlot;
import com.enderio.legacy_layout.machines.common.blocks.base.menu.PreviewMachineSlot;
import com.enderio.legacy_layout.machines.common.blocks.enchanter.EnchanterMenu;
import com.enderio.legacy_layout.machines.common.config.MachinesConfig;
import com.enderio.legacy_layout.machines.common.config.MachinesConfigLang;
import com.enderio.legacy_layout.machines.common.integrations.EnderIOMachinesSelfIntegration;
import com.enderio.legacy_layout.machines.common.lang.MachineEnumLang;
import com.enderio.legacy_layout.machines.common.lang.MachineLang;
import com.enderio.legacy_layout.machines.common.tag.MachineTags;
import com.enderio.legacy_layout.machines.data.advancements.MachinesAdvancementGenerator;
import com.enderio.legacy_layout.machines.data.datamap.RangeExtenderDataProvider;
import com.enderio.legacy_layout.machines.data.reagentdata.ReagentProvider;
import com.enderio.legacy_layout.machines.data.recipes.AlloyRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.EnchanterRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.FermentingRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.MachineRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.PaintingRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.SagMillRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.SlicingRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.SoulBindingRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.TankRecipeProvider;
import com.enderio.legacy_layout.machines.data.recipes.WeatherChangeRecipeProvider;
import com.enderio.legacy_layout.machines.data.souldata.SoulDataProvider;
import com.enderio.legacy_layout.machines.data.tag.MachineBlockTagsProvider;
import com.enderio.legacy_layout.machines.data.tag.MachineEntityTypeTagsProvider;
import com.enderio.legacy_layout.machines.data.tag.MachineItemTagsProvider;
import com.enderio.regilite.Regilite;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EnderIO.MOD_ID)
@Mod(EnderIO.MOD_ID)
public class EnderIO {
    public static final String MOD_ID = EnderIOAPI.NAMESPACE;

    public static IEventBus modEventBus;

    public static final Regilite REGILITE = new Regilite(MOD_ID);

    public EnderIO(IEventBus modEventBus, ModContainer modContainer) {
        EnderIO.modEventBus = modEventBus;

        // Ensure the enderio config subdirectory is present.
        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(EnderIOAPI.NAMESPACE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register config files
        // TODO: Config redesign.
        modContainer.registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON_SPEC, "enderio/base-common.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_SPEC, "enderio/base-client.toml");
        modContainer.registerConfig(ModConfig.Type.COMMON, MachinesConfig.COMMON_SPEC, "enderio/machines-common.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, MachinesConfig.CLIENT_SPEC, "enderio/machines-client.toml");

        BaseConfigLang.register();
        MachinesConfigLang.register();

        // Perform initialization and registration for everything so things are
        // registered.

        // ===== Base
        EIODataComponents.register(modEventBus);
        EIOCreativeTabs.register(modEventBus);
        EIOItems.register(modEventBus);
        EIOBlocks.register(modEventBus);
        EIOBlockEntities.register(modEventBus);
        EIOFluids.register(modEventBus);
        EIOTags.register();
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

        // ==== Machines
        MachineDataComponents.register(modEventBus);
        MachineTravelTargets.register(modEventBus);
        MachineBlocks.register(modEventBus);
        MachineBlockEntities.register(modEventBus);
        MachineMenus.register(modEventBus);
        MachineRecipes.register(modEventBus);
        MachineAttachments.register(modEventBus);

        MachineLang.register();
        MachineEnumLang.register();
        MachineTags.register();

        IntegrationManager.addIntegration(EnderIOMachinesSelfIntegration.INSTANCE);

        // ===== Conduits
        Conduits.register();
        ConduitTypes.register(modEventBus);
        ConduitBlockEntities.register(modEventBus);
        ConduitMenus.register(modEventBus);
        ConduitBlocks.register(modEventBus);
        ConduitItems.register(modEventBus);
        ConduitComponents.register(modEventBus);
        ConduitIngredientTypes.register(modEventBus);
        com.enderio.legacy_layout.conduits.common.integrations.Integrations.register();
        ConduitLang.register();

        REGILITE.register(modEventBus);

        // Register network packets
        EIONetworkPackets.registerPackets(modEventBus, modContainer);

        // Run datagen after registrate is finished.
        modEventBus.addListener(EventPriority.LOWEST, this::onGatherData);
        modEventBus.addListener(SoulVialItem::onCommonSetup);
        modEventBus.addListener(this::registerRegistries);
        Integrations.register();

        NeoForge.EVENT_BUS.addListener(PlayerMovementHandler::onPlayerTick);
    }

    private void registerRegistries(NewRegistryEvent event) {
        event.register(EnderIORegistries.TRAVEL_TARGET_TYPES);
        event.register(EnderIORegistries.TRAVEL_TARGET_SERIALIZERS);
        event.register(EnderIORegistries.CONDUIT_TYPE);
        event.register(EnderIORegistries.CONDUIT_DATA_TYPE);
        event.register(EnderIORegistries.CONDUIT_CONNECTION_CONFIG_TYPE);
        event.register(EnderIORegistries.CONDUIT_NODE_DATA_TYPE);
        event.register(EnderIORegistries.CONDUIT_NETWORK_CONTEXT_TYPE);
    }

    @SubscribeEvent
    private static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(EnderIORegistries.Keys.CONDUIT, Conduit.DIRECT_CODEC, Conduit.DIRECT_CODEC);
    }

    public void onGatherData(GatherDataEvent event) {
        event.createDatapackRegistryObjects(createDatapackEntriesBuilder(), Set.of(EnderIOAPI.NAMESPACE));

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // region Base

        EIODataProvider provider = new EIODataProvider("base");

        provider.addSubProvider(event.includeServer(), new MaterialRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new BlockRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new ItemRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new GlassRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new FireCraftingRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new FilterRecipeProvider(packOutput, registries));
        provider.addSubProvider(event.includeServer(), new EIOLootModifiersProvider(packOutput, registries));

        var b = new EIOBlockTagsProvider(packOutput, registries, existingFileHelper);
        provider.addSubProvider(event.includeServer(), b);
        provider.addSubProvider(event.includeServer(),
            new EIOItemTagsProvider(packOutput, registries, b.contentsGetter(), existingFileHelper));
        provider.addSubProvider(event.includeServer(),
            new EIOFluidTagsProvider(packOutput, registries, existingFileHelper));
        provider.addSubProvider(event.includeServer(),
            new EIOEntityTagsProvider(packOutput, registries, existingFileHelper));
        provider.addSubProvider(event.includeServer(), new AdvancementProvider(packOutput, registries,
            existingFileHelper, List.of(new EIOAdvancementGenerator())));
        provider.addSubProvider(event.includeServer(),
            new LootTableProvider(packOutput, Collections.emptySet(), List
                .of(new LootTableProvider.SubProviderEntry(ChestLootProvider::new, LootContextParamSets.CHEST)),
                registries));
        generator.addProvider(true, provider);

        // endregion

        // region Conduits

        EIODataProvider conduits = new EIODataProvider("conduits");

        conduits.addSubProvider(event.includeServer(),
            new ConduitTagProvider(packOutput, registries, event.getExistingFileHelper()));

        conduits.addSubProvider(event.includeServer(), new ConduitRecipes(packOutput, registries));

        event.getGenerator().addProvider(true, conduits);

        // endregion

        // region Machines

        EIODataProvider machines = new EIODataProvider("machines");

        machines.addSubProvider(event.includeServer(), new MachineRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new AlloyRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new EnchanterRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new FermentingRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new SagMillRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new SlicingRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new SoulBindingRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new TankRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new PaintingRecipeProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new SoulDataProvider(packOutput));
        machines.addSubProvider(event.includeServer(),
            new MachineEntityTypeTagsProvider(packOutput, registries, event.getExistingFileHelper()));
        var b1 = new MachineBlockTagsProvider(packOutput, registries, event.getExistingFileHelper());
        machines.addSubProvider(event.includeServer(), b1);
        machines.addSubProvider(event.includeServer(), new ReagentProvider(packOutput, registries)); // Reagent Data
                                                                                                     // needs to be
                                                                                                     // before
                                                                                                     // ItemTags
        machines.addSubProvider(event.includeServer(), new MachineItemTagsProvider(packOutput, registries,
            b1.contentsGetter(), event.getExistingFileHelper()));
        machines.addSubProvider(event.includeServer(), new RangeExtenderDataProvider(packOutput, registries));
        machines.addSubProvider(event.includeServer(), new WeatherChangeRecipeProvider(packOutput, registries));

        generator.addProvider(true, machines);
        machines.addSubProvider(event.includeServer(), new AdvancementProvider(packOutput, event.getLookupProvider(),
            event.getExistingFileHelper(), List.of(new MachinesAdvancementGenerator())));

        // endregion
    }

    private static RegistrySetBuilder createDatapackEntriesBuilder() {
        return new RegistrySetBuilder().add(EnderIORegistries.Keys.CONDUIT, Conduits::bootstrap);
    }

    @SubscribeEvent
    public static void sendIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo("inventorysorter", "slotblacklist", ItemFilterSlot.class::getName);
        InterModComms.sendTo("inventorysorter", "slotblacklist", FluidFilterSlot.class::getName);

        InterModComms.sendTo("inventorysorter", "slotblacklist", MachineSlot.class::getName);
        InterModComms.sendTo("inventorysorter", "slotblacklist", GhostMachineSlot.class::getName);
        InterModComms.sendTo("inventorysorter", "slotblacklist", PreviewMachineSlot.class::getName);
        InterModComms.sendTo("inventorysorter", "slotblacklist",
            EnchanterMenu.EnchanterOutputMachineSlot.class::getName);
    }

    @SubscribeEvent
    public static void addBuiltInPacks(final AddPackFindersEvent event) {
        event.addPackFinders(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "data/enderio/datapacks/farming_station"),
            PackType.SERVER_DATA, MachineLang.FARMING_STATION_EXPERIMENT, PackSource.FEATURE, false,
            Pack.Position.TOP);

        event.addPackFinders(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "data/enderio/datapacks/enderface"),
            PackType.SERVER_DATA, MachineLang.ENDERFACE_EXPERIMENT, PackSource.FEATURE, false, Pack.Position.TOP);
    }
}
