package com.enderio.enderio.api;

import com.enderio.enderio.api.filter.FilterMenuProvider;
import com.enderio.enderio.api.filter.FluidFilter;
import com.enderio.enderio.api.filter.ItemFilter;
import com.enderio.enderio.api.filter.SoulFilter;
import com.enderio.enderio.api.soul.binding.ISoulBindable;
import com.enderio.enderio.api.soul.storage.ISoulHandler;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;

public class EnderIOCapabilities {

    public static final class SideConfig {
        public static final BlockCapability<com.enderio.enderio.api.machine.SideConfig, Direction> BLOCK = BlockCapability
            .createSided(EnderIOAPI.loc("side_config"), com.enderio.enderio.api.machine.SideConfig.class);
    }

    public static final ItemCapability<FilterMenuProvider, Void> FILTER_MENU_PROVIDER = ItemCapability
        .createVoid(EnderIOAPI.loc("filter_menu_provider"), FilterMenuProvider.class);

    public static final ItemCapability<ItemFilter, Void> ITEM_FILTER = ItemCapability
        .createVoid(EnderIOAPI.loc("item_filter"), ItemFilter.class);

    public static final ItemCapability<FluidFilter, Void> FLUID_FILTER = ItemCapability
        .createVoid(EnderIOAPI.loc("fluid_filter"), FluidFilter.class);

    public static final ItemCapability<SoulFilter, Void> SOUL_FILTER = ItemCapability
        .createVoid(EnderIOAPI.loc("soul_filter"), SoulFilter.class);

    public static final class SoulBindable {
        public static final ItemCapability<ISoulBindable, Void> ITEM = ItemCapability
            .createVoid(EnderIOAPI.loc("soul_bindable"), ISoulBindable.class);

        public static final BlockCapability<ISoulBindable, Void> BLOCK = BlockCapability
            .createVoid(EnderIOAPI.loc("soul_bindable"), ISoulBindable.class);
    }

    public static final class SoulHandler {
        public static final ItemCapability<ISoulHandler, Void> ITEM = ItemCapability
            .createVoid(EnderIOAPI.loc("soul_handler"), ISoulHandler.class);

        public static final BlockCapability<ISoulHandler, @Nullable Direction> BLOCK = BlockCapability
            .createSided(EnderIOAPI.loc("soul_handler"), ISoulHandler.class);
    }
}
