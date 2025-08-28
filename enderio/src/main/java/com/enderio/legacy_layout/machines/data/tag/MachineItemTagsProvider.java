package com.enderio.legacy_layout.machines.data.tag;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.common.registration.EIOTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

//TODO remove when regilite support
public class MachineItemTagsProvider extends ItemTagsProvider {

    private static final Map<TagKey<Item>, List<Item>> itemTags = new HashMap<>();
    private static final Map<TagKey<Item>, List<TagKey<Item>>> tagTags = new HashMap<>();

    public MachineItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EnderIOAPI.NAMESPACE, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EIOTags.Items.CROPS, Tags.Items.CROPS);
        tag(EIOTags.Items.SEEDS, Tags.Items.SEEDS);
        tag(EIOTags.Items.MEAT, ItemTags.MEAT);
        tag(EIOTags.Items.EXPLOSIVES, net.minecraft.world.item.Items.TNT, net.minecraft.world.item.Items.FIREWORK_STAR, net.minecraft.world.item.Items.FIREWORK_ROCKET, net.minecraft.world.item.Items.FIRE_CHARGE);
        tag(EIOTags.Items.NATURAL_LIGHTS, net.minecraft.world.item.Items.GLOWSTONE_DUST, net.minecraft.world.item.Items.GLOWSTONE, net.minecraft.world.item.Items.SEA_LANTERN,
                net.minecraft.world.item.Items.SEA_PICKLE, net.minecraft.world.item.Items.GLOW_LICHEN, net.minecraft.world.item.Items.GLOW_BERRIES, net.minecraft.world.item.Items.GLOW_INK_SAC);
        tag(EIOTags.Items.SUNFLOWER, net.minecraft.world.item.Items.SUNFLOWER);
        tag(EIOTags.Items.BLAZE_POWDER, net.minecraft.world.item.Items.BLAZE_POWDER);
        tag(EIOTags.Items.AMETHYST, net.minecraft.world.item.Items.AMETHYST_SHARD);
        tag(EIOTags.Items.PRISMARINE, net.minecraft.world.item.Items.PRISMARINE_SHARD);
        tag(EIOTags.Items.CLOUD_COLD, net.minecraft.world.item.Items.SNOW, net.minecraft.world.item.Items.SNOW_BLOCK, net.minecraft.world.item.Items.SNOWBALL, net.minecraft.world.item.Items.ICE, net.minecraft.world.item.Items.PACKED_ICE,
                net.minecraft.world.item.Items.BLUE_ICE);
        tag(EIOTags.Items.LIGHTNING_ROD, net.minecraft.world.item.Items.LIGHTNING_ROD);
        tag(EIOTags.Items.WIND_CHARGES, net.minecraft.world.item.Items.WIND_CHARGE);

        itemTags.forEach((key, list) -> {
            var holder = tag(key);
            list.forEach(holder::add);
        });

        tagTags.forEach((key, tagKeys) -> {
            var holder = tag(key);
            tagKeys.forEach(holder::addTag);
        });
    }

    // helpers for tags. since tags can be added by external files, this prevents
    // duplicates.
    public static void tag(TagKey<Item> tag, Item item) {
        var items = itemTags.computeIfAbsent(tag, t -> new ArrayList<>());
        if (!items.contains(item))
            items.add(item);
    }

    public static void tag(TagKey<Item> tag, Item... itemList) {
        var items = itemTags.computeIfAbsent(tag, t -> new ArrayList<>());
        for (Item item : itemList) {
            if (!items.contains(item))
                items.add(item);
        }
    }

    public static void tag(TagKey<Item> tag, TagKey<Item> childTag) {
        var tags = tagTags.computeIfAbsent(tag, t -> new ArrayList<>());
        if (!tags.contains(childTag))
            tags.add(childTag);
    }
}
