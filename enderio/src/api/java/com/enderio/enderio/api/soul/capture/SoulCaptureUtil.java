package com.enderio.enderio.api.soul.capture;

import com.enderio.enderio.api.EnderIOTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class SoulCaptureUtil {
    @Nullable
    private static List<EntityType<?>> capturableEntityTypes = null;

    public static List<EntityType<?>> getCapturableEntityTypes() {
        if (capturableEntityTypes == null) {
            //noinspection unchecked
            capturableEntityTypes = ImmutableList.copyOf(
                BuiltInRegistries.ENTITY_TYPE.stream()
                    .filter(DefaultAttributes::hasSupplier)
                    .map(entityType -> (EntityType<? extends LivingEntity>) entityType)
                    .filter(entityType -> getCapturableStatus(entityType) == SoulCaptureStatus.CAPTURABLE)
                    .collect(Collectors.toList()));
        }

        return capturableEntityTypes;
    }

    /**
     * @param type EntityType to be checked
     * @return the status on how this entity should be handled for capture
     */
    public static SoulCaptureStatus getCapturableStatus(EntityType<? extends LivingEntity> type) {
        if (!type.canSerialize()) {
            return SoulCaptureStatus.INCOMPATIBLE;
        }

        if (type.is(EnderIOTags.EntityTypes.SOUL_CAPTURE_ALLOW_LIST)) {
            return SoulCaptureStatus.CAPTURABLE;
        }

        // TODO: Add bosses to soul vial blacklist tag and remove this check?
        // Will mean we have to remove the boss-specific message, but I think thats fine.
        if (type.is(Tags.EntityTypes.BOSSES)) {
            return SoulCaptureStatus.BOSS;
        }

        if (type.is(EnderIOTags.EntityTypes.SOUL_VIAL_BLACKLIST)) {
            return SoulCaptureStatus.BLACKLISTED;
        }

        return SoulCaptureStatus.CAPTURABLE;
    }
}
