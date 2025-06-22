package com.enderio.enderio.tempdata.tags;

import com.enderio.enderio.api.EnderIOTags;
import com.enderio.enderio.common.EnderIO;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EIOBlockTagsProvider extends BlockTagsProvider {

    public EIOBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnderIO.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(EnderIOTags.Blocks.BLOCKS_CONDUCTIVE_ALLOY)
                .addTag(EnderIOTags.Blocks.BLOCKS_COPPER_ALLOY)
                .addTag(EnderIOTags.Blocks.BLOCKS_DARK_STEEL)
                .addTag(EnderIOTags.Blocks.BLOCKS_END_STEEL)
                .addTag(EnderIOTags.Blocks.BLOCKS_ENERGETIC_ALLOY)
                .addTag(EnderIOTags.Blocks.BLOCKS_PULSATING_ALLOY)
                .addTag(EnderIOTags.Blocks.BLOCKS_REDSTONE_ALLOY)
                .addTag(EnderIOTags.Blocks.BLOCKS_SOULARIUM)
                .addTag(EnderIOTags.Blocks.BLOCKS_VIBRANT_ALLOY);
    }
}
