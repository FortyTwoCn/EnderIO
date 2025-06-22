package com.enderio.enderio.tempdata.tags;

import com.enderio.enderio.api.EnderIOTags;
import com.enderio.enderio.common.EnderIO;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EIOEntityTagsProvider extends EntityTypeTagsProvider {
    public EIOEntityTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, EnderIO.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(EnderIOTags.EntityTypes.SOUL_VIAL_BLACKLIST)
                .add(EntityType.WARDEN)
                .addTag(Tags.EntityTypes.CAPTURING_NOT_SUPPORTED);
    }
}
