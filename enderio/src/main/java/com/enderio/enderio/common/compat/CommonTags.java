package com.enderio.enderio.common.compat;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class CommonTags {
    private CommonTags() {
    }

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath("c", path);
    }

    public static class Items {
        private Items() {
        }

        public static final TagKey<Item> WRENCH = tag("tools/wrench");

        public static final TagKey<Item> DUSTS_LAPIS = tag("dusts/lapis");
        public static final TagKey<Item> DUSTS_COAL = tag("dusts/coal");
        public static final TagKey<Item> DUSTS_IRON = tag("dusts/iron");
        public static final TagKey<Item> DUSTS_GOLD = tag("dusts/gold");
        public static final TagKey<Item> DUSTS_COPPER = tag("dusts/copper");
        public static final TagKey<Item> DUSTS_TIN = tag("dusts/tin");
        public static final TagKey<Item> DUSTS_ENDER = tag("dusts/ender_pearl");
        public static final TagKey<Item> DUSTS_OBSIDIAN = tag("dusts/obsidian");
        public static final TagKey<Item> DUSTS_COBALT = tag("dusts/cobalt");
        public static final TagKey<Item> DUSTS_QUARTZ = tag("dusts/quartz");
        public static final TagKey<Item> DUSTS_SULFUR = tag("dusts/sulfur");
        public static final TagKey<Item> DUSTS_PRISMARINE = tag("dusts/prismarine");

        public static final TagKey<Item> STORAGE_BLOCKS_QUARTZ = tag("storage_blocks/quartz");
        public static final TagKey<Item> STORAGE_BLOCKS_AMETHYST = tag("storage_blocks/amethyst");

        public static final TagKey<Item> FERTILIZERS = tag("fertilizers");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, rl(name));
        }
    }

    public static class Fluids {
        private Fluids() {
        }

        public static final TagKey<Fluid> EXPERIENCE = tag("experience");

        private static TagKey<Fluid> tag(String name) {
            return TagKey.create(Registries.FLUID, rl(name));
        }
    }
}
