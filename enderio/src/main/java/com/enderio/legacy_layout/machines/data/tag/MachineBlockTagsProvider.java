package com.enderio.legacy_layout.machines.data.tag;

import com.enderio.enderio.api.EnderIOAPI;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class MachineBlockTagsProvider extends BlockTagsProvider {

    public MachineBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider,
            net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, EnderIOAPI.NAMESPACE, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
