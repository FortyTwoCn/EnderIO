package com.enderio.legacy_layout.machines.data.tag;

import com.enderio.enderio.api.EnderIOAPI;
import java.util.concurrent.CompletableFuture;

import com.enderio.enderio.common.registration.EIOTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;

public class MachineEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public MachineEntityTypeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider,
            net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, EnderIOAPI.NAMESPACE, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(EIOTags.EntityTypes.SPAWNER_BLACKLIST).addTag(Tags.EntityTypes.BOSSES).add(EntityType.WARDEN);
    }
}
