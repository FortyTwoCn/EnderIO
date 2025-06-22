package com.enderio.enderio.tempdata.tags;

import com.enderio.enderio.api.EnderIOTags;
import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.common.compat.CommonTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EIOItemTagsProvider extends ItemTagsProvider {

    public EIOItemTagsProvider(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> pProvider,
            CompletableFuture<TagLookup<Block>> pLookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(pPackOutput, pProvider, pLookup, EnderIO.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // copy(EnderIOTags.Blocks.CLEAR_GLASS, EnderIOTags.Items.CLEAR_GLASS);
        // copy(EnderIOTags.Blocks.FUSED_QUARTZ, EnderIOTags.Items.FUSED_QUARTZ);

        tag(Tags.Items.DUSTS).addTag(CommonTags.Items.DUSTS_COAL)
                .addTag(CommonTags.Items.DUSTS_LAPIS)
                .addTag(CommonTags.Items.DUSTS_QUARTZ)
                .addTag(CommonTags.Items.DUSTS_IRON)
                .addTag(CommonTags.Items.DUSTS_GOLD)
                .addTag(CommonTags.Items.DUSTS_COPPER)
                .addTag(CommonTags.Items.DUSTS_TIN)
                .addTag(CommonTags.Items.DUSTS_ENDER)
                .addTag(CommonTags.Items.DUSTS_OBSIDIAN)
                .addTag(CommonTags.Items.DUSTS_COBALT)
                .addTag(CommonTags.Items.DUSTS_PRISMARINE)
                .addTag(EnderIOTags.Items.DUSTS_GRAINS_OF_INFINITY)
                .addTag(EnderIOTags.Items.DUSTS_GRAINS_OF_PRESCIENCE)
                .addTag(EnderIOTags.Items.DUSTS_GRAINS_OF_VIBRANCY)
                .addTag(EnderIOTags.Items.DUSTS_GRAINS_OF_PIZEALLITY)
                .addTag(EnderIOTags.Items.DUSTS_GRAINS_OF_THE_END);

        tag(Tags.Items.INGOTS).addTag(EnderIOTags.Items.INGOTS_CONDUCTIVE_ALLOY)
                .addTag(EnderIOTags.Items.INGOTS_COPPER_ALLOY)
                .addTag(EnderIOTags.Items.INGOTS_DARK_STEEL)
                .addTag(EnderIOTags.Items.INGOTS_END_STEEL)
                .addTag(EnderIOTags.Items.INGOTS_ENERGETIC_ALLOY)
                .addTag(EnderIOTags.Items.INGOTS_PULSATING_ALLOY)
                .addTag(EnderIOTags.Items.INGOTS_REDSTONE_ALLOY)
                .addTag(EnderIOTags.Items.INGOTS_SOULARIUM)
                .addTag(EnderIOTags.Items.INGOTS_VIBRANT_ALLOY);

        tag(Tags.Items.NUGGETS).addTag(EnderIOTags.Items.NUGGETS_CONDUCTIVE_ALLOY)
                .addTag(EnderIOTags.Items.NUGGETS_COPPER_ALLOY)
                .addTag(EnderIOTags.Items.NUGGETS_DARK_STEEL)
                .addTag(EnderIOTags.Items.NUGGETS_END_STEEL)
                .addTag(EnderIOTags.Items.NUGGETS_ENERGETIC_ALLOY)
                .addTag(EnderIOTags.Items.NUGGETS_PULSATING_ALLOY)
                .addTag(EnderIOTags.Items.NUGGETS_REDSTONE_ALLOY)
                .addTag(EnderIOTags.Items.NUGGETS_SOULARIUM)
                .addTag(EnderIOTags.Items.NUGGETS_VIBRANT_ALLOY);

        tag(Tags.Items.STORAGE_BLOCKS).addTag(CommonTags.Items.STORAGE_BLOCKS_AMETHYST)
                .addTag(CommonTags.Items.STORAGE_BLOCKS_QUARTZ)
                .addTag(EnderIOTags.Items.BLOCKS_CONDUCTIVE_ALLOY)
                .addTag(EnderIOTags.Items.BLOCKS_COPPER_ALLOY)
                .addTag(EnderIOTags.Items.BLOCKS_DARK_STEEL)
                .addTag(EnderIOTags.Items.BLOCKS_END_STEEL)
                .addTag(EnderIOTags.Items.BLOCKS_ENERGETIC_ALLOY)
                .addTag(EnderIOTags.Items.BLOCKS_PULSATING_ALLOY)
                .addTag(EnderIOTags.Items.BLOCKS_REDSTONE_ALLOY)
                .addTag(EnderIOTags.Items.BLOCKS_SOULARIUM)
                .addTag(EnderIOTags.Items.BLOCKS_VIBRANT_ALLOY);

        tag(Tags.Items.GEMS).addTag(EnderIOTags.Items.GEMS_PULSATING_CRYSTAL)
                .addTag(EnderIOTags.Items.GEMS_VIBRANT_CRYSTAL)
                .addTag(EnderIOTags.Items.GEMS_ENDER_CRYSTAL)
                .addTag(EnderIOTags.Items.GEMS_ENTICING_CRYSTAL)
                .addTag(EnderIOTags.Items.GEMS_WEATHER_CRYSTAL)
                .addTag(EnderIOTags.Items.GEMS_PRESCIENT_CRYSTAL);

        tag(EnderIOTags.Items.GEARS)
                .addTag(EnderIOTags.Items.GEARS_IRON)
                .addTag(EnderIOTags.Items.GEARS_VIBRANT)
                .addTag(EnderIOTags.Items.GEARS_ENERGIZED)
                .addTag(EnderIOTags.Items.GEARS_DARK_STEEL);

        tag(EnderIOTags.Items.INSULATION_METAL).addTag(CommonTags.Items.DUSTS_IRON).addTag(CommonTags.Items.DUSTS_TIN);

        // Common tags
        tag(CommonTags.Items.DUSTS_PRISMARINE).add(Items.PRISMARINE_SHARD);

        tag(CommonTags.Items.STORAGE_BLOCKS_QUARTZ).add(Items.QUARTZ_BLOCK);

        tag(CommonTags.Items.STORAGE_BLOCKS_AMETHYST).add(Items.AMETHYST_BLOCK);
    }
}
