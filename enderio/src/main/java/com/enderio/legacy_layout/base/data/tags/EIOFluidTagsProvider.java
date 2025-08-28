package com.enderio.legacy_layout.base.data.tags;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOFluids;
import com.enderio.enderio.common.registration.EIOTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EIOFluidTagsProvider extends FluidTagsProvider {

    public EIOFluidTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, EnderIOAPI.NAMESPACE, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(EIOTags.Fluids.COLD_FIRE_IGNITER_FUEL).add(EIOFluids.VAPOR_OF_LEVITY.getSource());
        tag(EIOTags.Fluids.STAFF_OF_LEVITY_FUEL).add(EIOFluids.VAPOR_OF_LEVITY.getSource());
    }
}
