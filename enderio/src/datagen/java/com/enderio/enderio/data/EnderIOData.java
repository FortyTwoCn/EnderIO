package com.enderio.enderio.data;

import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.data.advancement.EIOAdvancementGenerator;
import com.enderio.enderio.data.loot.ChestLootProvider;
import com.enderio.enderio.data.loot.EIOLootModifiersProvider;
import com.enderio.enderio.data.recipe.BlockRecipeProvider;
import com.enderio.enderio.data.recipe.FilterRecipeProvider;
import com.enderio.enderio.data.recipe.FireCraftingRecipeProvider;
import com.enderio.enderio.data.recipe.GlassRecipeProvider;
import com.enderio.enderio.data.recipe.ItemRecipeProvider;
import com.enderio.enderio.data.recipe.MaterialRecipeProvider;
import com.enderio.enderio.data.tags.EIOBlockTagsProvider;
import com.enderio.enderio.data.tags.EIOEntityTagsProvider;
import com.enderio.enderio.data.tags.EIOFluidTagsProvider;
import com.enderio.enderio.data.tags.EIOItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(EnderIO.MOD_ID)
public class EnderIOData {
    public EnderIOData(IEventBus eventBus) {
        eventBus.addListener(EventPriority.LOWEST, this::onGatherData);
    }

    private void onGatherData(GatherDataEvent event) {
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
